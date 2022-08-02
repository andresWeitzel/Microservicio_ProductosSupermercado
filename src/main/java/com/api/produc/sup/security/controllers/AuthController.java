package com.api.produc.sup.security.controllers;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.produc.sup.security.dto.JwtDTO;
import com.api.produc.sup.security.dto.LoginUsuarioDTO;
import com.api.produc.sup.security.dto.SigninUsuarioDTO;
import com.api.produc.sup.security.entities.Rol;
import com.api.produc.sup.security.entities.Usuario;
import com.api.produc.sup.security.enums.TipoRol;
import com.api.produc.sup.security.jwt.JwtProvider;
import com.api.produc.sup.security.services.RolService;
import com.api.produc.sup.security.services.UsuarioService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RolService rolService;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody SigninUsuarioDTO signinUsuario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Campos o Email Inválidos!!", HttpStatus.BAD_REQUEST);
		}

		if (usuarioService.existsByUsername(signinUsuario.getUsername())) {
			return new ResponseEntity<String>("El Username del Usuario ya existe!!", HttpStatus.BAD_REQUEST);
		}

		if (usuarioService.existsByEmail(signinUsuario.getEmail())) {
			return new ResponseEntity<String>("El Email del Usuario ya existe!!", HttpStatus.BAD_REQUEST);
		}
		if(signinUsuario.getNombre().isBlank() 
				|| signinUsuario.getUsername().isBlank() 
				|| signinUsuario.getPassword().isBlank()
				|| signinUsuario.getPassword().isBlank()
				|| signinUsuario.getEmail().isBlank())
		{
			return new ResponseEntity<String>("No se permiten campos vacios!!", HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = new Usuario(signinUsuario.getNombre(), signinUsuario.getUsername(),
				passwordEncoder.encode(signinUsuario.getPassword()), signinUsuario.getEmail());

		Set<Rol> roles = new HashSet<>();

		
		roles.add(rolService.getByRol(TipoRol.ROLE_USER).get());

		
		if (signinUsuario.getRoles().contains("admin")) {
			roles.add(rolService.getByRol(TipoRol.ROLE_ADMIN).get());
		}

		usuario.setRoles(roles);

		usuarioService.addUsuario(usuario);

		return new ResponseEntity<SigninUsuarioDTO>(signinUsuario, HttpStatus.OK);
	}
	
	
	
	

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuarioDTO loginUsuario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Campos Inválidos!!", HttpStatus.BAD_REQUEST);
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		JwtDTO jwtDto = new JwtDTO(jwt);

		return new ResponseEntity<JwtDTO>(jwtDto, HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(@RequestBody JwtDTO jwtDto) throws ParseException{
		
		String token = jwtProvider.refreshToken(jwtDto);
		
		JwtDTO jwtRefresh = new JwtDTO(token);
		
		return new ResponseEntity<JwtDTO> (jwtRefresh, HttpStatus.OK);
		
		
	}
	

}
