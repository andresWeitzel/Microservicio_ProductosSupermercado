package com.api.rest.productos.supermercado.repositories;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.productos.supermercado.entities.Usuario;

@Repository
public interface I_UsuarioRepository extends JpaRepository<Usuario, Serializable>
, PagingAndSortingRepository<Usuario, Serializable>  {
	
	
	
	// ============= MÉTODOS DE BÚSQUEDA ===================

	public abstract Usuario findById(UUID id);
	

	@Query("select c from Usuario c where c.usuario like %:usuario%")
	public abstract Usuario findByUsuario(String usuario);
	
	@Query("select c from Usuario c where c.usuario like %:usuario%")
	public abstract Page<Usuario> findByUsuario(String usuario, Pageable pageable);
	
	@Query("select c from Usuario c where c.password like %:password%")
	public abstract Page<Usuario> findByPassword(String password, Pageable pageable);

	@Query("select c from Usuario c where c.rol like %:rol%")
	public abstract Page<Usuario> findByRol(String rol, Pageable pageable);

	
	
	


}
