package com.api.rest.productos.supermercado.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.productos.supermercado.entities.Producto;
import com.api.rest.productos.supermercado.services.ProductoService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	// ==============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ==============================================

	// ================
	// ===== POST =====
	// ================
	@Operation(summary = "Inserción de un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Insertado el Producto Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Insertar el Producto. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Inserción del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@PostMapping("/")
	public ResponseEntity<?> addProducto(@RequestBody Producto producto) {

		try {

			productoService.addProducto(producto);

			return new ResponseEntity<Producto>(producto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// ===============
	// ===== PUT =====
	// ===============
	@Operation(summary = "Actualización de un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Actualizado el Producto Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Actualizar el Producto. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Actualización del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@PutMapping("/")
	public ResponseEntity<?> updateProducto(@RequestBody Producto producto) {

		try {
			productoService.updateProducto(producto);

			return new ResponseEntity<Producto>(producto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// =================
	// ===== DELETE ====
	// =================
	@Operation(summary = "Eliminación de un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Eliminado el Producto Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Eliminado el Producto. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "LaEliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProducto(@RequestBody UUID id) {

		try {
			productoService.deleteProducto(id);

			return new ResponseEntity<Producto>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// =================
	// ===== GETALL ====
	// =================
	@Operation(summary = "Listado Paginado de Productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer el Listado de Productos. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/listado")
	@PreAuthorize("hasRole('ADMIN')")
	public Page<Producto> getAllProducto(Pageable pageable) {

		return productoService.findAllProducto(pageable);

	}

	// ==================================================
	// ============= MÉTODOS HTTP BÚSQUEDA =============
	// ==================================================

	// ===================
	// ===== GET BY ID ===
	// ===================
	// ---PRODUCTO POR ID---
	@Operation(summary = "Producto según su Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Producto según su ID Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer el Producto según su ID. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Producto según su ID no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/id/{id}")
	public Producto getById(@PathVariable("id") UUID id) {
		return productoService.findById(id);
	}

	// =======================
	// ===== GET BY CODIGO ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR CODIGO---
	@Operation(summary = "Listado de Productos o Producto según su Código")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su CODIGO Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su CODIGO. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su CODIGO no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/codigo/{codigo}")
	public Page<Producto> getByCodigo(@PathVariable("codigo") String codigo, Pageable pageable) {
		return productoService.findByCodigo(codigo, pageable);
	}

	// =======================
	// ===== GET BY IMAGEN ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR IMAGEN---
	@Operation(summary = "Listado de Productos o Producto según su Imagen")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su IMAGEN Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su IMAGEN. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su IMAGEN no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/imagen/{imagen}")
	public Page<Producto> getByImagen(@PathVariable("imagen") String imagen, Pageable pageable) {
		return productoService.findByImagen(imagen, pageable);
	}

	// =======================
	// ===== GET BY NOMBRE ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR NOMBRE---
	@Operation(summary = "Listado de Productos o Producto según su Nombre")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su NOMBRE Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su NOMBRE. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su NOMBRE no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/nombre/{nombre}")
	public Page<Producto> getByNombre(@PathVariable("nombre") String nombre, Pageable pageable) {
		return productoService.findByNombre(nombre, pageable);

	}

	// ======================
	// ===== GET BY MARCA ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR MARCA---
	@Operation(summary = "Listado de Productos o Producto según su Marca")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su MARCA Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su MARCA. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su MARCA no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/marca/{marca}")
	public Page<Producto> getByMarca(@PathVariable("marca") String marca, Pageable pageable) {
		return productoService.findByMarca(marca, pageable);
	}

	// =====================
	// ===== GET BY TIPO ===
	// =====================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR TIPO---
	@Operation(summary = "Listado de Productos o Producto según su Tipo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su TIPO Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su TIPO. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su TIPO no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/tipo/{tipo}")
	public Page<Producto> getByTipo(@PathVariable("tipo") String tipo, Pageable pageable) {
		return productoService.findByTipo(tipo, pageable);
	}

	// ======================
	// ===== GET BY GRUPO ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR GRUPO---
	@Operation(summary = "Listado de Productos o Producto según su Grupo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su GRUPO Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su GRUPO. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su GRUPO no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/grupo/{grupo}")
	public Page<Producto> getByGrupo(@PathVariable("grupo") String grupo, Pageable pageable) {
		return productoService.findByGrupo(grupo, pageable);
	}

	// ======================
	// ===== GET BY PESO ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR PESO---
	@Operation(summary = "Listado de Productos o Producto según su Peso")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su PESO Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su PESO. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su PESO no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/peso/{peso}")
	public Page<Producto> getByPeso(@PathVariable("peso") double peso, Pageable pageable) {
		return productoService.findByPeso(peso, pageable);
	}

	// ==============================
	// ===== GET BY PRECIO_UNIDAD ===
	// ==============================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR PRECIO UNIDAD---
	@Operation(summary = "Listado de Productos o Producto según su Precio Unidad")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su PRECIO_UNIDAD Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su PRECIO_UNIDAD. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su PRECIO_UNIDAD no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/precio-unidad/{precioUnidad}")
	public Page<Producto> getByPrecioUnidad(@PathVariable("precioUnidad") double precioUnidad, Pageable pageable) {
		return productoService.findByPrecioUnidad(precioUnidad, pageable);
	}
	
	
	// ======================
	// ===== GET BY STOCK ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR STOCK---
	@Operation(summary = "Listado de Productos o Producto según su STOCK")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos o Producto según su STOCK Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Productos o Producto según su STOCK. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Productos o Producto según su STOCK no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/stock/{stock}")
	public Page<Producto> getByStock(@PathVariable("stock") int stock, Pageable pageable) {
		return productoService.findByStock(stock, pageable);
	}

}
