package com.api.produc.sup.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.produc.sup.security.entities.Usuario;
import com.api.produc.sup.security.repository.I_UsuarioRepository;


@Service
@Transactional
public class UsuarioService {

    @Autowired
    I_UsuarioRepository iUsuarioRepository;

    public Optional<Usuario> getByUsername(String username){
        return iUsuarioRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return iUsuarioRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return iUsuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        iUsuarioRepository.save(usuario);
    }
}
