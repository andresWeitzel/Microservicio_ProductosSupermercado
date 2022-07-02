package com.api.produc.sup.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.produc.sup.security.entities.Rol;
import com.api.produc.sup.security.enums.TipoRol;

@Repository
public interface I_RolRepository extends JpaRepository<Rol, Long> {
	
	Optional<Rol> findById(long id);
	
    Optional<Rol> findByRol(TipoRol rol);
}

