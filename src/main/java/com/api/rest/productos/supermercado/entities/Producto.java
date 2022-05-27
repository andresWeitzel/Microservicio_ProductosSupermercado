package com.api.rest.productos.supermercado.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;

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
