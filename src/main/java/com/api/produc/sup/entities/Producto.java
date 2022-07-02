package com.api.produc.sup.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "productos")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
	private long id;

	@Column(name = "codigo", nullable=false, length=255, unique=true)
	private String codigo;

	@Column(name = "imagen", nullable=true, length=600)
	private String imagen;

	@Column(name = "nombre", nullable=false, length=100)
	private String nombre;

	@Column(name = "marca", nullable=false, length=100)
	private String marca;

	@Column(name = "tipo", nullable=false, length=100)
	private String tipo;

	@Column(name = "grupo", nullable=false, length=100)
	private String grupo;

	@Column(name = "peso", nullable=false)
	private double peso;

	@Column(name = "precio_unidad", nullable=false)
	private double precioUnidad;

	@Column(name = "stock", nullable=false)
	private int stock;

}

