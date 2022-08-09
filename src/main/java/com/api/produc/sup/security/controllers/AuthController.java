package com.api.produc.sup.security.controllers;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;

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

	
	
	
	

	// =====================
	// ===== POST SIGNIN ===
	// =====================
	// ---INSERCIÓN DE USUARIOS---
	@ApiOperation(value = "Inserción de Usuarios", notes="Devuelve el usuario registrado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
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
	
	
	
	
	// =====================
	// ===== POST LOGIN ===
	// =====================
	// ---VALIDACIÓN DE USUARIOS---
	@ApiOperation(value = "Validación de Usuarios", notes="Devuelve el token si se ha autenticado correctamente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuarioDTO loginUsuario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Campos Inválidos!!", HttpStatus.BAD_REQUEST);
		}
		
		if (!(usuarioService.existsByUsername(loginUsuario.getUsername()))
				|| !(usuarioService.existsByPassword(loginUsuario.getPassword())) ) {
			return new ResponseEntity<String>("El Usuario no existe. Comprobar username y password!!", HttpStatus.BAD_REQUEST);
		}
		

		if(loginUsuario.getUsername().isBlank() 
				|| loginUsuario.getPassword().isBlank()
				)
		{
			return new ResponseEntity<String>("No se permiten campos vacios!!", HttpStatus.BAD_REQUEST);
		}


		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		JwtDTO jwtDto = new JwtDTO(jwt);

		return new ResponseEntity<JwtDTO>(jwtDto, HttpStatus.OK);
	}
	
	

	
	
	
	// ============================
	// ===== POST REFRESH TOKEN ===
	// ============================
	// ---VALIDACIÓN DE USUARIOS REFRESCADO---
	@ApiOperation(value = "Validación de Usuarios Refrescado", notes="Devuelve un nuevo token si es que el token caducado es válido/autenticado ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Producto Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Producto Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Producto. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el producto. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido actualizar el producto. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor")
			})
	
	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(@RequestBody JwtDTO jwtDto) throws ParseException{
		
		String token = jwtProvider.refreshToken(jwtDto);
		
		JwtDTO jwtRefresh = new JwtDTO(token);
		
		return new ResponseEntity<JwtDTO> (jwtRefresh, HttpStatus.OK);
		
		
	}
	

}
