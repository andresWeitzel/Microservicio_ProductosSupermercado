
package com.api.produc.sup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.produc.sup.entities.Producto;
import com.api.produc.sup.exc.productos.ProductoIdMismatchExc;
import com.api.produc.sup.exc.productos.ProductoNotFoundExc;
import com.api.produc.sup.repositories.I_ProductoRepository;
import org.apache.logging.log4j.Logger;

@Service
public class ProductoService {

	@Autowired
	private I_ProductoRepository iProductoRepository;

	//Logs
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductoService.class);

	// =============== MÉTODOS CRUD ====================

	// ==================
	// ===== INSERT =====
	// ==================
	public void addProducto(Producto producto) {
		try {
			if (producto == null) {
				logger.error("ERROR addProducto : EL PRODUCTO " + producto + " ES NULO!!");
				throw new ProductoNotFoundExc("EL PRODUCTO ES NULO");
			} else if (producto.getCodigo() == "" || producto.getImagen() == "" || producto.getNombre() == ""
					|| producto.getMarca() == "" || producto.getTipo() == "" || producto.getGrupo() == ""
					|| producto.getPeso() == 0.0 || producto.getPrecioUnidad() == 0.0 || producto.getStock() == 0.0) {
				logger.error(
						"ERROR addProducto : LOS VALORES DE LOS CAMPOS DEL PRODUCTO " + producto + " NO SON VÁLIDOS!!");
				throw new ProductoNotFoundExc("VALORES DE CAMPOS NO VÁLIDOS");

			} else if (producto == iProductoRepository.findByCodigo(producto.getCodigo())) {
				logger.error("ERROR addProducto : NO SE PUEDE REPETIR EL PRODUCTO " + producto + " !!");
				throw new ProductoNotFoundExc("NO SE PUEDE REPETIR UN PRODUCTO");

			} else {
				iProductoRepository.save(producto);
			}

		} catch (Exception e) {
			logger.error(
					"ERROR addProducto : EL PRODUCTO " + producto + " NO SE HA INSERTADO EN LA DB!! CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO AGREGAR EL PRODUCTO ", e, false, true);
		}
	}

	// ==================
	// ===== UPDATE =====
	// ==================
	public void updateProducto(long id, Producto producto) {
		try {
			if (producto == null) {
				logger.error("ERROR updateProducto : EL PRODUCTO " + producto + " ES NULO!!");
				throw new ProductoNotFoundExc("EL PRODUCTO ES NULO");
			} else if (id <= 0) {
				logger.error("ERROR updateProducto : EL ID DEL PRODUCTO " + producto + " NO ES VÁLIDO!!");
				throw new ProductoIdMismatchExc("ID INVÁLIDO");

			} else if (producto.getCodigo() == "" || producto.getImagen() == "" || producto.getNombre() == ""
					|| producto.getMarca() == "" || producto.getTipo() == "" || producto.getGrupo() == ""
					|| producto.getPeso() == 0.0 || producto.getPrecioUnidad() == 0.0 || producto.getStock() == 0.0) {
				logger.error(
						"ERROR addProducto : LOS VALORES DE LOS CAMPOS DEL PRODUCTO " + producto + " NO SON VÁLIDOS!!");
				throw new ProductoNotFoundExc("VALORES DE CAMPOS NO VÁLIDOS");

			} else {

				Producto nuevoProducto = iProductoRepository.findById(id);
				nuevoProducto.setCodigo(producto.getCodigo());
				nuevoProducto.setImagen(producto.getImagen());
				nuevoProducto.setNombre(producto.getNombre());
				nuevoProducto.setMarca(producto.getMarca());
				nuevoProducto.setTipo(producto.getTipo());
				nuevoProducto.setGrupo(producto.getGrupo());
				nuevoProducto.setPeso(producto.getPeso());
				nuevoProducto.setPrecioUnidad(producto.getPrecioUnidad());
				nuevoProducto.setStock(producto.getStock());

				iProductoRepository.save(nuevoProducto);
			}

		} catch (Exception e) {
			logger.error("ERROR updateProducto : EL PRODUCTO " + producto
					+ " NO SE HA ACTUALIZADO EN LA DB!! CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ACTUALIZAR EL PRODUCTO ", e, false, true);
		}
	}

	// ==================
	// ===== DELETE =====
	// ==================
	public void deleteProducto(long id) {
		try {

			if (id <= 0) {
				logger.error("ERROR deleteProducto : EL PRODUCTO CON EL ID " + id + " NO ES VÁLIDO!!");
				throw new ProductoIdMismatchExc("EL PRODUCTO CON EL ID NO ES VÁLIDO");
			} else {
				iProductoRepository.delete(iProductoRepository.getById(id));

				logger.info("SE HA ELIMINADO CORRECTAMENTE EL PRODUCTO CON EL ID " + id);
			}

		} catch (Exception e) {
			logger.error("ERROR deleteProducto : EL PRODUCTO CON EL ID " + id
					+ " NO SE HA ELIMINDO EN LA DB!!CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ELIMINAR EL PRODUCTO ", e, false, true);
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

			logger.error("ERROR findAllProducto : NO SE HAN LISTADO LOS PRODUCTOS. CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS ", e);
		}
	}

	// ==========================
	// ===== GET ALL FILTER =====
	// ==========================
	// ------- LISTADO CON FILTER PAGINADO ---------
	public Page<Producto> findAllFilterProducto(String filter, Pageable pageable) {

		try {
			return iProductoRepository.findAllFilter(filter, pageable);

		} catch (Exception e) {

			logger.error("ERROR findAllFilterProducto : NO SE HAN LISTADO LOS PRODUCTOS FILTRADOS. CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS FILTRADO ", e);
		}
	}

	// ==================================
	// ===== GET ALL EXCLUDE FILTER =====
	// ==================================
	// ------- LISTADO CON EXCLUDE FILTER PAGINADO ---------
	public Page<Producto> findAllExcludeFilterProducto(String filter, Pageable pageable) {

		try {
			return iProductoRepository.findAllExcludeFilter(filter, pageable);

		} catch (Exception e) {

			logger.error("ERROR findAllExcludeFilter : NO SE HAN LISTADO LOS PRODUCTOS FILTRADOS. CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS FILTRADO ", e);
		}
	}

	// =============== MÉTODOS DE BUSQUEDA ====================

	// ===============
	// ===== GET =====
	// ===============
	// ------ ID --------
	public Producto findById(long id) {
		try {

			if (id <= 0) {

				logger.error("ERROR finById : EL PRODUCTO CON EL ID " + id + " NO ES VÁLIDO!!");

				throw new ProductoIdMismatchExc("EL PRODUCTO SEGÚN EL ID NO ES VÁLIDO");

			} else {

				return iProductoRepository.findById(id);

			}

		} catch (Exception e) {
			logger.error("ERROR findById : NO SE HA ENCONTRADO EL PRODUCTO CON EL ID SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL ID SOLICITADO ", e);
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
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL ID SOLICITADO ", e);
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
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON LA IMAGEN SOLICITADO ", e);
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
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL NOMBRE SOLICITADO ", e);
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
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON LA MARCA SOLICITADO ", e);
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
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL TIPO SOLICITADO ", e);
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
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL GRUPO SOLICITADO ", e);
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

				throw new ProductoNotFoundExc("EL PRODUCTO SEGÚN EL PESO INDICADO NO ES VÁLIDO");

			} else {

				return iProductoRepository.findByPeso(peso, pageable);
			}
		} catch (Exception e) {
			logger.error("ERROR findByPeso : NO SE HA ENCONTRADO EL PRODUCTO CON EL PESO SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL PESO SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ PESO CON FILTRO MAX--------
	public Page<Producto> findByPesoFilter(double maxPeso, Pageable pageable) {
		try {

			if (maxPeso <= 0) {

				logger.error(
						"ERROR findByPesoFilter : EL PRODUCTO SEGÚN EL PESO MÁXIMO DE " + maxPeso + "NO ES VÁLIDO!!");

				throw new ProductoNotFoundExc("EL PRODUCTO SEGÚN LOS VALORES DE PESOS INDICADO NO ES VÁLIDO");

			} else {

				return iProductoRepository.findByPesoFilter(maxPeso, pageable);
			}
		} catch (Exception e) {
			logger.error("ERROR findByPesoFilter : EL PRODUCTO SEGÚN EL PESO MÁXIMO DE " + maxPeso
					+ "NO ES VÁLIDO!! CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL PESO SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ PESO CON FILTRO MAX & MIN--------
	public Page<Producto> findByPesoFilter(double minPeso, double maxPeso, Pageable pageable) {
		try {

			if (minPeso <= 0.0 || minPeso > maxPeso) {

				logger.error("ERROR findByPesoFilter : EL PRODUCTO SEGÚN EL PESO MÍN DE " + minPeso
						+ " Y EL PESO MÁXIMO DE " + maxPeso + "NO ES VÁLIDO!!");

				throw new ProductoNotFoundExc("EL PRODUCTO SEGÚN LOS VALORES DE PESOS INDICADO NO ES VÁLIDO");

			} else {

				return iProductoRepository.findByPesoFilter(minPeso, maxPeso, pageable);
			}
		} catch (Exception e) {
			logger.error("ERROR findByPesoFilter : EL PRODUCTO SEGÚN EL PESO MÍN DE " + minPeso
					+ " Y EL PESO MÁXIMO DE " + maxPeso + "NO ES VÁLIDO!! CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO ENTRE LOS PESOS SOLICITADOS ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ PRECIO_UNIDAD --------
	public Page<Producto> findByPrecioUnidad(double precioUnidad, Pageable pageable) {
		try {
			if (precioUnidad <= 0.0) {

				logger.error("ERROR finByPrecioUnidad : EL PRODUCTO CON EL PRECIO POR UNIDAD " + precioUnidad
						+ " NO ES VÁLIDO!!");

				throw new ProductoNotFoundExc("EL PRODUCTO SEGÚN EL PRECIO POR UNIDAD NO ES VÁLIDO");

			} else {

				return iProductoRepository.findByPrecioUnidad(precioUnidad, pageable);

			}

		} catch (Exception e) {
			logger.error(
					"ERROR findByPrecioUnidad : NO SE HA ENCONTRADO EL PRODUCTO CON EL PRECIO POR UNIDAD SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL PRECIO POR UNIDAD SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ PRECIO_UNIDAD (MAX) --------
	public Page<Producto> findByPrecioUnidadFilter(double maxPrecioUnidad, Pageable pageable) {
		try {
			if (maxPrecioUnidad <= 0.0) {

				logger.error("ERROR findByPrecioUnidadFilter : EL PRODUCTO CON EL PRECIO POR UNIDAD " + maxPrecioUnidad
						+ " NO ES VÁLIDO!!");

				throw new ProductoNotFoundExc("EL PRODUCTO SEGÚN EL PRECIO POR UNIDAD NO ES VÁLIDO");

			} else {

				return iProductoRepository.findByPrecioUnidadFilter(maxPrecioUnidad, pageable);

			}

		} catch (Exception e) {
			logger.error(
					"ERROR findByPrecioUnidadFilter : NO SE HA ENCONTRADO EL PRODUCTO CON EL PRECIO POR UNIDAD SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL PRECIO POR UNIDAD SOLICITADO ", e);
		}

	}

	// ===============
	// ===== GET =====
	// ===============
	// ------ PRECIO_UNIDAD (MIN & MAX) --------
	public Page<Producto> findByPrecioUnidadFilter(double minPrecioUnidad, double maxPrecioUnidad, Pageable pageable) {
		try {
			if (maxPrecioUnidad <= 0.0) {

				logger.error("ERROR findByPrecioUnidadFilter : EL PRODUCTO CON EL PRECIO POR UNIDAD MINIMO DE "
						+ minPrecioUnidad + " Y MÁXIMO DE " + +maxPrecioUnidad + " NO ES VÁLIDO!!");

				throw new ProductoNotFoundExc("EL PRODUCTO SEGÚN EL PRECIO POR UNIDAD NO ES VÁLIDO");

			} else {
				return iProductoRepository.findByPrecioUnidadFilter(minPrecioUnidad, maxPrecioUnidad, pageable);
			}

		} catch (Exception e) {
			logger.error(
					"ERROR findByPrecioUnidadFilter : NO SE HA ENCONTRADO EL PRODUCTO CON EL PRECIO POR UNIDAD MÍNIMO DE "
							+ minPrecioUnidad + " Y MÁXIMO DE " + maxPrecioUnidad + " SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON LOS PRECIOS POR UNIDAD SOLICITADO ", e);
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

				throw new ProductoNotFoundExc("EL PRODUCTO SEGÚN EL STOCK NO ES VÁLIDO");

			} else {

				return iProductoRepository.findByStock(stock, pageable);

			}

		} catch (Exception e) {
			logger.error(
					"ERROR findByStock : NO SE HA ENCONTRADO EL PRODUCTO CON EL STOCK SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundExc("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL STOCK  SOLICITADO ", e);
		}

	}

}
