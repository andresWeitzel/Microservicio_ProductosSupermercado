package com.api.produc.sup.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.produc.sup.security.entities.Usuario;
import com.api.produc.sup.security.entities.UsuarioDetails;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByUsername(username).get();
        return UsuarioDetails.build(usuario);
    }
}
