
package com.api.produc.sup.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.produc.sup.entities.Producto;
import com.api.produc.sup.services.ProductoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/v1/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	// ==============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ==============================================

	// ================
	// ===== POST =====
	// ================
	@ApiOperation(value = "Inserción de un Producto", notes="Devuelve el producto agregado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Insertado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Insertado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Insertar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para insertar un producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido insertar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Inserción del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	@ApiOperation(value = "Actualización de un Producto", notes="Devuelve el producto actualizado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Actualizar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Actualización del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateProducto(@PathVariable("id") long id, @RequestBody Producto producto) {

		try {
			productoService.updateProducto(id, producto);

			return new ResponseEntity<Producto>(producto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// =================
	// ===== DELETE ====
	// =================
	@ApiOperation(value = "Eliminación de un Producto", notes="Devuelve el producto eliminado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteProducto(@PathVariable("id") long id) {

		try {
			productoService.deleteProducto(id);

			return new ResponseEntity<Producto>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// =================
	// ===== GET ALL ====
	// =================
	@ApiOperation(value = "Listado Paginado de Productos", notes="Devuelve el/los producto/s paginados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado el/los Producto/s Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado el/los Producto/s Correctamente"),
			@ApiResponse(code = 400,  message = "No se ha/han podido Listar los Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para listar el/los producto/s. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha/han podido listar el/los producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "El Listado de/los Producto/s no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/listado")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getAllProducto(Pageable pageable) {

		return productoService.findAllProducto(pageable);

	}

	// =========================
	// ===== GET ALL FILTER ====
	// ========================
	@ApiOperation(value = "Listado Paginado por Filtro de Productos", notes="Devuelve el/los producto/s paginados por filtro")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado el/los Producto/s Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado el/los Producto/s Correctamente"),
			@ApiResponse(code = 400,  message = "No se ha/han podido Listar los Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para listar el/los producto/s. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha/han podido listar el/los producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "El Listado de/los Producto/s no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/listado-filter/{filtro}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getAllFilterProducto(@PathVariable("filtro") String filtro, Pageable pageable) {

		return productoService.findAllFilterProducto(filtro,pageable);

	}

	// ==================================================
	// ============= MÉTODOS HTTP BÚSQUEDA =============
	// ==================================================

	// ===================
	// ===== GET BY ID ===
	// ===================
	// ---PRODUCTO POR ID---
	@ApiOperation(value = "Producto según su ID", notes="Devuelve el producto según el id solicitado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/id/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Producto getById(@PathVariable("id") long id) {
		return productoService.findById(id);
	}

	// =======================
	// ===== GET BY CODIGO ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR CODIGO---
	@ApiOperation(value = "Producto/s Paginados según su Código", notes="Devuelve el/los producto/s según el código solicitado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/codigo/{codigo}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getByCodigo(@PathVariable("codigo") String codigo, Pageable pageable) {
		return productoService.findByCodigo(codigo, pageable);
	}

	// =======================
	// ===== GET BY IMAGEN ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR IMAGEN---
	@ApiOperation(value = "Producto/s Paginados según su Imagen", notes="Devuelve el/los producto/s según la imagen solicitado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/imagen/{imagen}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getByImagen(@PathVariable("imagen") String imagen, Pageable pageable) {
		return productoService.findByImagen(imagen, pageable);
	}

	// =======================
	// ===== GET BY NOMBRE ===
	// =======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR NOMBRE---
	@ApiOperation(value = "Producto/s Paginados según su Nombre", notes="Devuelve el/los producto/s según el nombre solicitado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/nombre/{nombre}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getByNombre(@PathVariable("nombre") String nombre, Pageable pageable) {
		return productoService.findByNombre(nombre, pageable);

	}

	// ======================
	// ===== GET BY MARCA ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR MARCA---
	@ApiOperation(value = "Producto/s Paginados según su Marca", notes="Devuelve el/los producto/s según la marca solicitado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/marca/{marca}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getByMarca(@PathVariable("marca") String marca, Pageable pageable) {
		return productoService.findByMarca(marca, pageable);
	}

	// =====================
	// ===== GET BY TIPO ===
	// =====================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR TIPO---
	@ApiOperation(value = "Producto/s Paginados según su Tipo", notes="Devuelve el/los producto/s según el tipo solicitado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/tipo/{tipo}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getByTipo(@PathVariable("tipo") String tipo, Pageable pageable) {
		return productoService.findByTipo(tipo, pageable);
	}

	// ======================
	// ===== GET BY GRUPO ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR GRUPO---
	@ApiOperation(value = "Producto/s Paginados según su Grupo", notes="Devuelve el/los producto/s según el grupo solicitado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/grupo/{grupo}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getByGrupo(@PathVariable("grupo") String grupo, Pageable pageable) {
		return productoService.findByGrupo(grupo, pageable);
	}

	// ======================
	// ===== GET BY PESO ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR PESO---
	@ApiOperation(value = "Producto/s Paginados según su Peso", notes="Devuelve el/los producto/s según el peso solicitado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/peso/{peso}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getByPeso(@PathVariable("peso") double peso, Pageable pageable) {
		return productoService.findByPeso(peso, pageable);
	}

	// ==============================
	// ===== GET BY PRECIO_UNIDAD ===
	// ==============================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR PRECIO UNIDAD---
	@ApiOperation(value = "Producto/s Paginados según su Precio por Unidad", notes="Devuelve el/los producto/s según el precio por unidad solicitado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/precio-unidad/{precioUnidad}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getByPrecioUnidad(@PathVariable("precioUnidad") double precioUnidad, Pageable pageable) {
		return productoService.findByPrecioUnidad(precioUnidad, pageable);
	}

	// ======================
	// ===== GET BY STOCK ===
	// ======================
	// ---LISTADO DE PRODUCTOS O PRODUCTO POR STOCK---
	@ApiOperation(value = "Producto/s Paginados según su Stock", notes="Devuelve el/los producto/s según el stock solicitado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@GetMapping("/stock/{stock}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Page<Producto> getByStock(@PathVariable("stock") int stock, Pageable pageable) {
		return productoService.findByStock(stock, pageable);
	}

}
