package com.api.produc.sup.security.repositories;


import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.produc.sup.security.entities.Usuario;


@Repository
public interface I_UsuarioRepository extends JpaRepository<Usuario, Serializable>
, PagingAndSortingRepository<Usuario, Serializable> {
	
	
	// ============= MÉTODOS DE BÚSQUEDA ===================

	public Optional<Usuario> findById(long id);

	@Query("select c from Usuario c where c.nombre like %:nombre%")
	public Page<Usuario>findByNombre(String nombre, Pageable pageable);
	
	
	public Optional<Usuario> findByUsername(String username);
	
	@Query("select c from Usuario c where c.password like %:password%")
	public Page<Usuario> findByPassword(String password, Pageable pageable);


	@Query("select c from Usuario c where c.email like %:email%")
	public Page<Usuario> findByEmail(String email, Pageable pageable);
	
	
	public Page<Usuario> findAll(Pageable pageable);

	
	
	// ============= MÉTODOS DE COMPROBACIONES ===================


    boolean existsByUsername(String username);
    
    
    boolean existsByEmail(String email);


    
}
