package com.api.rest.productos.supermercado.security.config.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.rest.productos.supermercado.entities.Usuario;
import com.api.rest.productos.supermercado.exceptions.usuario.UsuarioNotFoundException;
import com.api.rest.productos.supermercado.repositories.I_UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService{

	
	//== Iny. Dep. ==
	@Autowired
	private I_UsuarioRepository iUsuarioRepository;
	
	
	//Logica para obtener el usuario de la base de datos
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 
		Usuario usuario = iUsuarioRepository.findByUsuario(username);
		
		if(usuario==null) {throw new UsuarioNotFoundException("Usuario No Encontrado");}
		
		return new User(usuario.getUsuario() , encoder.encode(usuario.getPassword())
				, obtenerPermisos(usuario.getRol()));
	}
	
	
	
	
	public List<GrantedAuthority> obtenerPermisos(String rol){
		
		
		List<GrantedAuthority> auths = new ArrayList();
		
		auths.add(new SimpleGrantedAuthority(rol));

		return auths;
	}
}
