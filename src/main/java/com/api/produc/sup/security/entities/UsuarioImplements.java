package com.api.produc.sup.security.entities;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioImplements implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String username;
	private String password;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;


/**
 * Creacion de un usuario con su rol
 * @param usuario
 * @return
 */
	public static UsuarioImplements build(Usuario usuario) {
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRol().name())).collect(Collectors.toList());
		return new UsuarioImplements(usuario.getNombre(), usuario.getUsername(), usuario.getPassword()
				, usuario.getEmail(),authorities);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}








}
