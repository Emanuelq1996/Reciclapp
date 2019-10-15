package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.model.Producto;
import com.example.repo.ProductoRepository;
import com.example.service.ProductoServicio;
import com.example.service.ProductoServicioInterface;
 
@RestController
@RequestMapping (path ="/")
public class WebController {
  
  @Autowired
  ProductoServicioInterface servicio;
  
  @GetMapping
  public String check(){
	  return "Funcionando...";
  }
  
  
  @GetMapping(path = "/save") //ESTO SOLO ES PARA PROBAR ANTES DE TENER LA BASE ARMADA
  public String process(){ 
	  List<Producto> lista = new ArrayList<Producto>();
	  lista.add(new Producto("7797470007952", "Marolio Tomate Triturado x500g","Carton","Chico"));
	  lista.add(new Producto("7793653230841", "Gaseosa Torasso x 3L","Botella","Grande"));
	  lista.add(new Producto("7790748235149", "Merluza En Aceite Puglisi 380 Gr","Lata","Chico"));
	  lista.add(new Producto("7790499001857", "Choclo Desgranado Cremoso Blanco La Banda 340 Gr","Lata","Mediano"));
	  lista.add(new Producto("7790580982102", "Arvejas Secas Remojadas En Lata Arcor 320 Gr","Lata","Chico"));
	  
	  servicio.saveAll(lista);//Hay veces que agrega dos veces las cosas
	  lista.clear();
    return "Done"; //ACA CAMBIE SAVE POR SAVEALL DEL ORIGINAL PORQUE SINO NO ME DEJABA
  }
  
  
  @GetMapping(path = "/findall")
  public List<Producto> findAll(){
    return servicio.findAll();
  }
  
  @GetMapping(path = "/findbyid")
  public String findById(@RequestParam("id") long id){
    return servicio.findById(id).toString();
  }
  
 /* @GetMapping(path = "/findbybarcode")
  public Producto fetchDataByCodigoBarras(@RequestParam("barcode") String barcode){
    return servicio.findByBarcode(barcode);
  }
  
  @GetMapping(path = "/testbarcode/{barcode}")
  public String testDataByCodigoBarras(@PathParam(value = "barcode") String barcode){
    return servicio.findByBarcode(barcode).toString();
  }
  
  @GetMapping(path = "/hardcoded")
  public String hardcodedDataByCodigoBarras(){
    return servicio.findByBarcode("7790580982102").toString();
  }  */
  
	@GetMapping("/facturitas/{barcode}")
	public ResponseEntity<Producto> getEmployeeById(@PathVariable(value="barcode") String barcode){
		Producto prod=servicio.findByBarcode(barcode);
		if(prod==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(prod);
	}
	
	
	//hasta aca funciona
	
	@PostMapping("/NuevoProducto")
	public Producto createEmployee(@Valid @RequestBody Producto prod) {
		return servicio.save(prod);
	}
	
	@GetMapping("/producto/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable(value="id") Long id){
		
		Producto prod=servicio.findById(id);
		
		if(prod==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(prod);
		
	}
	
	@PutMapping("/actualizarProducto/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable(value="id") Long id,@Valid @RequestBody Producto actualizado){
		
		Producto prod=servicio.findById(id);
		if(prod==null) {
			return ResponseEntity.notFound().build();
		}
		
		prod.setCodigoBarras(actualizado.getCodigoBarras());
		prod.setDescripcion(actualizado.getDescripcion());
		prod.setCategoria(actualizado.getCategoria());
		prod.setSubcategoria(actualizado.getSubcategoria());
		
		Producto updateProducto=servicio.save(prod);
		return ResponseEntity.ok().body(updateProducto);	
		
	}
	
	@DeleteMapping("/eliminarProducto/{id}")
	public ResponseEntity<Producto> deleteProducto(@PathVariable(value="id") Long empid){
		
		Producto emp=servicio.findById(empid);
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		servicio.delete(emp);
		
		return ResponseEntity.ok().build();
  
	}
}