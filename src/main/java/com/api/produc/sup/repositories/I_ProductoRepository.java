
package com.api.produc.sup.repositories;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.produc.sup.entities.Producto;

@Repository
public interface I_ProductoRepository
		extends JpaRepository<Producto, Serializable>, PagingAndSortingRepository<Producto, Serializable> {

	// ============= MÉTODOS DE BÚSQUEDA ===================

	public abstract Producto findById(long id);

	@Query("select c from Producto c where lower(c.codigo) like lower(concat('%', :codigo, '%'))")
	public abstract Page<Producto> findByCodigo(String codigo, Pageable pageable);

	public abstract Producto findByCodigo(String codigo);

	@Query("select c from Producto c where lower(c.imagen) like lower(concat('%', :imagen, '%'))")
	public abstract Page<Producto> findByImagen(String imagen, Pageable pageable);

	@Query("select c from Producto c where lower(c.nombre) like lower(concat('%', :nombre, '%'))")
	public abstract Page<Producto> findByNombre(String nombre, Pageable pageable);

	@Query("select c from Producto c where lower(c.marca) like lower(concat('%', :marca, '%'))")
	public abstract Page<Producto> findByMarca(String marca, Pageable pageable);

	@Query("select c from Producto c where lower(c.tipo) like lower(concat('%', :tipo, '%'))")
	public abstract Page<Producto> findByTipo(String tipo, Pageable pageable);

	@Query("select c from Producto c where lower(c.grupo) like lower(concat('%', :grupo, '%'))")
	public abstract Page<Producto> findByGrupo(String grupo, Pageable pageable);

	public abstract Page<Producto> findByPeso(double peso, Pageable pageable);

	@Query("select u from Producto u where u.peso <= ?1")
	public abstract Page<Producto> findByPesoFilter(double maxPeso, Pageable pageable);

	@Query("select u from Producto u where u.peso >= ?1 and u.peso <= ?2")
	public abstract Page<Producto> findByPesoFilter(double minPeso, double maxPeso, Pageable pageable);

	public abstract Page<Producto> findByPrecioUnidad(double precioUnidad, Pageable pageable);

	@Query("select u from Producto u where u.precioUnidad <= ?1")
	public abstract Page<Producto> findByPrecioUnidadFilter(double maxPrecioUnidad, Pageable pageable);

	@Query("select u from Producto u where u.precioUnidad >= ?1 and u.precioUnidad <= ?2")
	public abstract Page<Producto> findByPrecioUnidadFilter(double minPrecioUnidad, double maxPrecioUnidad,
			Pageable pageable);

	public abstract Page<Producto> findByStock(int stock, Pageable pageable);

	public abstract Page<Producto> findAll(Pageable pageable);

	@Query("select c from Producto c where concat( lower(c.codigo), lower(c.imagen) , lower(c.nombre), lower(c.marca), lower(c.tipo), lower(c.grupo) ) like lower( concat ( '%', ?1, '%'))")
	public abstract Page<Producto> findAllFilter(String filtro, Pageable pageable);

	@Query("select c from Producto c where not concat (lower(c.codigo), lower(c.imagen) "
			+ ", lower(c.nombre), lower(c.marca), lower(c.tipo), lower(c.grupo) "
			+ ") like lower( concat ( '%', ?1, '%'))")
	public abstract Page<Producto> findAllExcludeFilter(String filtro, Pageable pageable);
}
