package com.api.produc.sup.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.produc.sup.security.entities.Rol;
import com.api.produc.sup.security.enums.TipoRol;
import com.api.produc.sup.security.repositories.I_RolRepository;


@Service
@Transactional
public class RolService {

    @Autowired
    I_RolRepository rolRepository;

    public Optional<Rol> getByRol(TipoRol rol){
        return rolRepository.findByRol(rol);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
