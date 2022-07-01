package com.api.produc.sup.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.api.produc.sup.security.entities.Rol;
import com.api.produc.sup.security.enums.TipoRol;
import com.api.produc.sup.security.services.RolService;


/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */

/*
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol(TipoRol.ROLE_ADMIN);
        Rol rolUser = new Rol(TipoRol.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
        
       
         
    }
  
    
   
}
  */

