package com.api.rest.productos.supermercado.repositories;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.productos.supermercado.entities.Producto;

@Repository
public interface I_ProductoRepository extends JpaRepository<Producto, Serializable>
, PagingAndSortingRepository<Producto, Serializable> {

	// ============= MÉTODOS DE BÚSQUEDA ===================

	public abstract Producto findById(UUID id);

	@Query("select c from Producto c where c.codigo like %:codigo%")
	public abstract Page<Producto> findByCodigo(String codigo, Pageable pageable);

	@Query("select c from Producto c where c.imagen like %:imagen%")
	public abstract Page<Producto> findByImagen(String imagen, Pageable pageable);

	@Query("select c from Producto c where c.nombre like %:nombre%")
	public abstract Page<Producto> findByNombre(String nombre, Pageable pageable);

	@Query("select c from Producto c where c.marca like %:marca%")
	public abstract Page<Producto> findByMarca(String marca, Pageable pageable);

	@Query("select c from Producto c where c.tipo like %:tipo%")
	public abstract Page<Producto> findByTipo(String tipo, Pageable pageable);

	@Query("select c from Producto c where c.tipo like %:grupo%")
	public abstract Page<Producto> findByGrupo(String grupo, Pageable pageable);

	public abstract Page<Producto> findByPeso(double peso, Pageable pageable);

	public abstract Page<Producto> findByPrecioUnidad(double precioUnidad, Pageable pageable);

	public abstract Page<Producto> findByStock(int stock, Pageable pageable);

}
