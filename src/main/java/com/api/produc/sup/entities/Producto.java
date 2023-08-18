package com.api.produc.sup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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

	@Column(name = "codigo", nullable = false, unique = true, length = 255)
	@NotNull(message="El Código no puede ser null")
	@NotEmpty(message="El Código no puede ser vacío")
	@NotBlank(message="El Código no puede estar en blanco")
	@Size(min = 6, max = 255, message = "El Código debe ser mayor o igual a 6 caracteres y menor o igual a 255 caracteres")
	private String codigo;

	
	@Column(name = "imagen", nullable = true, length = 600)
	@NotNull(message="La Imagen no puede ser null")
	@NotEmpty(message="La Imagen no puede ser vacío")
	@NotBlank(message="La imagen no puede estar en blanco")
	@Size(min = 20, max = 600, message = "La Imagen debe ser mayor o igual a 20 caracteres y menor o igual a 600 caracteres")
	private String imagen;

	@Column(name = "nombre", nullable = false, length = 100)
	@NotNull(message="El Nombre no puede ser null")
	@NotEmpty(message="El Nombre no puede ser vacío")
	@NotBlank(message="El Nombre no puede estar en blanco")
	@Size(min = 4, max = 100, message = "El Nombre debe ser mayor o igual a 4 caracteres y menor o igual a 100 caracteres")
	private String nombre;

	@Column(name = "marca", nullable = false, length = 100)
	@NotNull(message="La Marca no puede ser null")
	@NotEmpty(message="La Marca no puede ser vacío")
	@NotBlank(message="La Marca no puede estar en blanco")
	@Size(min = 4, max = 100, message = "La Marca debe ser mayor o igual a 4 caracteres y menor o igual a 100 caracteres")
	private String marca;

	@Column(name = "tipo", nullable = false, length = 150)
	@NotNull(message="El Tipo no puede ser null")
	@NotEmpty(message="El Tipo no puede ser vacío")
	@NotBlank(message="El Tipo no puede estar en blanco")
	@Size(min = 4, max = 150, message = "El Tipo debe ser mayor o igual a 4 caracteres y menor o igual a 150 caracteres")
	private String tipo;

	@Column(name = "grupo", nullable = false, length = 200)
	@NotNull(message="El Grupo no puede ser null")
	@NotEmpty(message="El Grupo no puede ser vacío")
	@NotBlank(message="El Grupo no puede estar en blanco")
	@Size(min = 4, max = 200, message = "El Grupo debe ser mayor o igual a 4 caracteres y menor o igual a 200 caracteres")
	private String grupo;

	@Column(name = "peso", nullable = false)
	@NotNull(message="El Peso no puede ser null")
	@DecimalMin(value = "0.10", message = "El Peso debe ser mayor o igual a 0.10g")
    @DecimalMax(value = "600.0", message = "El Peso debe ser menor o igual a 600kg")
	private double peso;

	@Column(name = "precio_unidad", nullable = false)
	@NotNull(message="El Precio_Unidad no puede ser null")
	@DecimalMin(value = "10", message = "El Precio_Unidad debe ser mayor o igual a $10")
    @DecimalMax(value = "10000", message = "El Precio_Unidad debe ser menor o igual a $10000")
	private double precioUnidad;

	@Column(name = "stock", nullable = false)
	@NotNull(message="El Stock no puede ser null")
	@Min(value = 10, message = "El Stock debe ser mayor o igual a 10 Unidades")
    @Max(value = 3000, message = "El Stock debe ser menor o igual a 3000 Unidades")
	private int stock;

}
