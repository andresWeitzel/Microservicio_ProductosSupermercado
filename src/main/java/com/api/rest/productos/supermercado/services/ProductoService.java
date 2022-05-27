package com.api.rest.productos.supermercado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.rest.productos.supermercado.entities.Producto;
import com.api.rest.productos.supermercado.exceptions.producto.ProductoIdMismatchException;
import com.api.rest.productos.supermercado.exceptions.producto.ProductoNotFoundException;
import com.api.rest.productos.supermercado.repositories.I_ProductoRepository;

import java.util.UUID;

import org.apache.logging.log4j.Logger;

@Service
public class ProductoService {

	// ============ INYEC. DEP ============
	@Autowired
	private I_ProductoRepository iProductoRepository;

	// =============== LOGS ====================
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductoService.class);

	// =============== MÉTODOS CRUD ====================

	// ==================
	// ===== INSERT =====
	// ==================
	public void addProducto(Producto producto) {
		try {
			if (producto == null) {
				logger.error("ERROR addProducto : EL PRODUCTO " + producto + " ES NULO!!");
				throw new ProductoNotFoundException("EL PRODUCTO ES NULO");
			} else {
				iProductoRepository.save(producto);
			}

		} catch (Exception e) {
			logger.error("ERROR addProducto : EL PRODUCTO " + producto + " NO SE HA INSERTADO EN LA DB!!");
			throw new ProductoNotFoundException("NO SE PUDO AGREGAR EL PRODUCTO ", e, false, true);
		}
	}

	// ==================
	// ===== UPDATE =====
	// ==================
	public void updateProducto(Producto producto) {
		try {
			if (producto == null) {
				logger.error("ERROR updateProducto : EL PRODUCTO " + producto + " ES NULO!!");
				throw new ProductoNotFoundException("EL PRODUCTO ES NULO");
			} else {
				iProductoRepository.save(producto);
			}

		} catch (Exception e) {
			logger.error("ERROR updateProducto : EL PRODUCTO " + producto + " NO SE HA ACTUALIZADO EN LA DB!!");
			throw new ProductoNotFoundException("NO SE PUDO ACTUALIZAR EL PRODUCTO ", e, false, true);
		}
	}

	// ==================
	// ===== DELETE =====
	// ==================
	public void deleteProducto(UUID id) {
		try {

			if (id == null) {
				logger.error("ERROR deleteProducto : EL PRODUCTO CON EL ID " + id + " NO ES VÁLIDO!!");
				throw new ProductoIdMismatchException("EL PRODUCTO CON EL ID NO ES VÁLIDO");
			} else {
				iProductoRepository.delete(iProductoRepository.getById(id));

				logger.info("SE HA ELIMINADO CORRECTAMENTE EL PRODUCTO CON EL ID " + id);
			}

		} catch (Exception e) {
			logger.error("ERROR deleteProducto : EL PRODUCTO CON EL ID " + id + " NO SE HA ELIMINDO EN LA DB!!");
			throw new ProductoNotFoundException("NO SE PUDO ELIMINAR EL PRODUCTO ", e, false, true);
		}
	}

	// ===================
	// ===== GET ALL =====
	// ===================
	// ------- LISTADO PAGINADO ---------
	public Page<Producto> findAllProducto(Pageable pageable) {

		try {
			return iProductoRepository.findAll(pageable);
		} catch (Exception e) {

			logger.error("ERROR getAllProducto : NO SE HAN LISTADO LOS PRODUCTOS. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS ", e);
		}
	}

	// =============== MÉTODOS DE BUSQUEDA ====================

	// ===============
	// ===== GET =====
	// ===============
	// ------ ID --------
	public Producto findById(UUID id) {
		try {

			if (id == null) {

				logger.error("ERROR finById : EL PRODUCTO CON EL ID " + id + " NO ES VÁLIDO!!");

				throw new ProductoIdMismatchException("EL PRODUCTO SEGÚN EL ID NO ES VÁLIDO");

			} else {

				return iProductoRepository.findById(id);

			}

		} catch (Exception e) {
			logger.error("ERROR findById : NO SE HA ENCONTRADO EL PRODUCTO CON EL ID SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL ID SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ CODIGO --------
	public Page<Producto> findByCodigo(String codigo, Pageable pageable) {
		try {

			return iProductoRepository.findByCodigo(codigo, pageable);

		} catch (Exception e) {
			logger.error("ERROR findByCodigo : NO SE HA ENCONTRADO EL PRODUCTO CON EL ID SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL ID SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ IMAGEN --------
	public Page<Producto> findByImagen(String imagen, Pageable pageable) {
		try {

			return iProductoRepository.findByImagen(imagen, pageable);

		} catch (Exception e) {
			logger.error(
					"ERROR findByImagen : NO SE HA ENCONTRADO EL PRODUCTO CON LA IMAGEN SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON LA IMAGEN SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ NOMBRE --------
	public Page<Producto> findByNombre(String nombre, Pageable pageable) {
		try {

			return iProductoRepository.findByNombre(nombre, pageable);

		} catch (Exception e) {
			logger.error(
					"ERROR findByNombre : NO SE HA ENCONTRADO EL PRODUCTO CON EL NOMBRE SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL NOMBRE SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ MARCA --------
	public Page<Producto> findByMarca(String marca, Pageable pageable) {
		try {

			return iProductoRepository.findByMarca(marca, pageable);

		} catch (Exception e) {
			logger.error(
					"ERROR findByMarca : NO SE HA ENCONTRADO EL PRODUCTO CON LA MARCA SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON LA MARCA SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ TIPO --------
	public Page<Producto> findByTipo(String tipo, Pageable pageable) {
		try {

			return iProductoRepository.findByTipo(tipo, pageable);

		} catch (Exception e) {
			logger.error("ERROR findByTipo : NO SE HA ENCONTRADO EL PRODUCTO CON EL TIPO SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL TIPO SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ GRUPO --------
	public Page<Producto> findByGrupo(String grupo, Pageable pageable) {
		try {

			return iProductoRepository.findByGrupo(grupo, pageable);

		} catch (Exception e) {
			logger.error(
					"ERROR findByGrupo : NO SE HA ENCONTRADO EL PRODUCTO CON EL GRUPO SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL GRUPO SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ PESO --------
	public Page<Producto> findByPeso(double peso, Pageable pageable) {
		try {
			
			if (peso <= 0.0) {

				logger.error("ERROR finByPeso : EL PRODUCTO SEGÚN EL PESO " + peso + " NO ES VÁLIDO!!");

				throw new ProductoNotFoundException("EL PRODUCTO SEGÚN EL PESO INDICADO NO ES VÁLIDO");

			} else {

				return iProductoRepository.findByPeso(peso, pageable);
			}
		} catch (Exception e) {
			logger.error("ERROR findByPeso : NO SE HA ENCONTRADO EL PRODUCTO CON EL PESO SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL PESO SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ PRECIO_UNIDAD --------
	public Page<Producto> findByPrecioUnidad(double precioUnidad, Pageable pageable) {
		try {
			if (precioUnidad <= 0.0) {

				logger.error("ERROR finByPrecioUnidad : EL PRODUCTO CON EL PRECIO POR UNIDAD " + precioUnidad + " NO ES VÁLIDO!!");

				throw new ProductoNotFoundException("EL PRODUCTO SEGÚN EL PRECIO POR UNIDAD NO ES VÁLIDO");

			} else {

				return iProductoRepository.findByPrecioUnidad(precioUnidad, pageable);

			}
		
		} catch (Exception e) {
			logger.error(
					"ERROR findByPrecioUnidad : NO SE HA ENCONTRADO EL PRODUCTO CON EL PRECIO POR UNIDAD SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL PRECIO POR UNIDAD SOLICITADO ",
					e);
		}

	}
	
	
	// ===============
	// ===== GET =====
	// ===============
	// ------ STOCK --------
	public Page<Producto> findByStock(int stock, Pageable pageable) {
		try {
			
			if (stock <= 0) {

				logger.error("ERROR finByStock : EL PRODUCTO CON EL STOCK " + stock + " NO ES VÁLIDO!!");

				throw new ProductoNotFoundException("EL PRODUCTO SEGÚN EL STOCK NO ES VÁLIDO");

			} else {

				return iProductoRepository.findByStock(stock, pageable);


			}
		
			
		} catch (Exception e) {
			logger.error(
					"ERROR findByStock : NO SE HA ENCONTRADO EL PRODUCTO CON EL STOCK SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL STOCK  SOLICITADO ",
					e);
		}

	}

}
