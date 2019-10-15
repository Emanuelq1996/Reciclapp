package com.example.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "productos")
public class Producto  implements Serializable {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
 
  @Column(name = "barcode")
  private String barcode;
 
  @Column(name = "descripcion")
  private String descripcion;
  
  @Column(name = "categoria")
  private String categoria; 
  
  @Column(name = "subcategoria")
  private String subcategoria;
 
  public Producto(String codigoBarras, String descripcion, String categoria, String subcategoria) {
    this.barcode=codigoBarras;
	this.descripcion=descripcion;
	this.categoria=categoria;
	this.subcategoria=subcategoria;
  }
  
  protected Producto(){
  }
  	public String getCodigoBarras() {
		return barcode;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.barcode = codigoBarras;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
 
  @Override
  public String toString() {
    return String.format("Producto[id=%d, barcode='%s', descripcion='%s', categoria='%s', subcategoria='%s']", id, barcode, descripcion, categoria, subcategoria);
  }
}