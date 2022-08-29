package com.api.produc.sup.security.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.produc.sup.security.dto.SigninUsuarioDTO;
import com.api.produc.sup.security.entities.Rol;
import com.api.produc.sup.security.entities.Usuario;
import com.api.produc.sup.security.enums.TipoRol;
import com.api.produc.sup.security.exc.usuario.UsuarioIdMismatchExc;
import com.api.produc.sup.security.exc.usuario.UsuarioNotFoundExc;
import com.api.produc.sup.security.repositories.I_UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	I_UsuarioRepository iUsuarioRepository;

	@Autowired
	RolService rolService;

	// =============== LOGS ====================
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(UsuarioService.class);

	// =============== MÉTODOS CRUD ====================

	// ==================
	// ===== INSERT =====
	// ==================
	public void addUsuarioDTO(SigninUsuarioDTO usuarioDTO) {
		try {
			if (usuarioDTO == null) {
				logger.error("ERROR addUsuario : EL USUARIO " + usuarioDTO + " ES NULO!!");
				throw new UsuarioNotFoundExc("EL USUARIO ES NULO");
			} else {

				Usuario usuarioEncode = new Usuario(usuarioDTO.getNombre(), usuarioDTO.getUsername(),
						new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()), usuarioDTO.getEmail());

				Set<Rol> roles = new HashSet<>();

				if (usuarioDTO.getRoles().contains("user") || usuarioDTO.getRoles().isEmpty()) {
					roles.add(rolService.getByRol(TipoRol.ROLE_USER).get());
				}

				if (usuarioDTO.getRoles().contains("admin")) {
					roles.add(rolService.getByRol(TipoRol.ROLE_ADMIN).get());
					roles.add(rolService.getByRol(TipoRol.ROLE_USER).get());
				}

				usuarioEncode.setRoles(roles);

				iUsuarioRepository.save(usuarioEncode);
			}

		} catch (Exception e) {
			logger.error("ERROR addUsuario : EL USUARIO " + usuarioDTO + " NO SE HA INSERTADO EN LA DB!!CAUSADO POR "+e);
			throw new UsuarioNotFoundExc("NO SE PUDO AGREGAR EL USUARIO ", e, false, true);
		}
	}

	// ==================
	// ===== INSERT =====
	// ==================
	public void addUsuario(Usuario usuario) {
		try {
			if (usuario == null) {
				logger.error("ERROR addUsuario : EL USUARIO " + usuario + " ES NULO!!");
				throw new UsuarioNotFoundExc("EL USUARIO ES NULO");
			} else {

	
				iUsuarioRepository.save(usuario);
			}

		} catch (Exception e) {
			logger.error("ERROR addUsuario : EL USUARIO " + usuario + " NO SE HA INSERTADO EN LA DB!!CAUSADO POR "+e);
			throw new UsuarioNotFoundExc("NO SE PUDO AGREGAR EL USUARIO ", e, false, true);
		}
	}

	// ==================
	// ===== UPDATE =====
	// ==================
	public void updateUsuario(long id, SigninUsuarioDTO usuarioDTO) {
		try {
			Optional<Usuario> usuarioDb = this.iUsuarioRepository.findById(id);
			
			if (usuarioDTO == null) {
				logger.error("ERROR updateUsuario : EL USUARIO " + usuarioDTO + " ES NULO!!");
				throw new UsuarioNotFoundExc("EL USUARIO ES NULO");
			}else if (id < 0){
				logger.error("ERROR updateUsuario : EL ID  NO ES VÁLIDO!!");
				throw new UsuarioNotFoundExc("EL ID DEL USUARIO NO ES VÁLIDO");
			
			}else if (usuarioDTO.getNombre() == "" 
					|| usuarioDTO.getPassword() == "" || usuarioDTO.getEmail() == ""){
				logger.error("ERROR updateProducto : LOS VALORES DE LOS CAMPOS DEL USUARIO " 
					+ usuarioDTO + " NO SON VÁLIDOS!!");
				throw new UsuarioNotFoundExc("VALORES DE CAMPOS NO VÁLIDOS");
			
			}else {
				

				Set<Rol> roles = new HashSet<>();

				if (usuarioDTO.getRoles().contains("ROLE_USER") || usuarioDTO.getRoles().isEmpty()) {
					roles.add(rolService.getByRol(TipoRol.ROLE_USER).get());
				}

				if (usuarioDTO.getRoles().contains("ROLE_ADMIN")) {
					roles.add(rolService.getByRol(TipoRol.ROLE_ADMIN).get());
					roles.add(rolService.getByRol(TipoRol.ROLE_USER).get());
				}

				
				Usuario usuarioUpdate = usuarioDb.get();
				
				
				System.out.println("USUARIO PRE UPDATE "+usuarioUpdate);
			
				
				usuarioUpdate.setId(id);
				usuarioUpdate.setNombre(usuarioDTO.getNombre());
				usuarioUpdate.setUsername(usuarioDTO.getUsername());
				usuarioUpdate.setPassword(new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()));
				usuarioUpdate.setEmail(usuarioDTO.getEmail());
				usuarioUpdate.setRoles(roles);
				

				System.out.println("USUARIO POST UPDATE "+usuarioUpdate);

				
				iUsuarioRepository.save(usuarioUpdate);
				
				logger.info("SE HA ACTUALIZADO CORRECTAMENTE EL NUEVO USUARIO " + usuarioUpdate);
			}
		} catch (Exception e) {
			logger.error("ERROR updateUsuario : EL USUARIO " + usuarioDTO + " NO SE HA ACTUALIZADO EN LA DB!!CAUSADO POR "+e);
			throw new UsuarioNotFoundExc("NO SE PUDO ACTUALIZAR EL USUARIO ", e, false, true);
		}

	}

	// ==================
	// ===== DELETE =====
	// ==================
	public void deleteUsuario(long id) {
		try {
			if (id <= 0) {
				logger.error("ERROR deleteUsuario : EL USUARIO " + id + " NO ES VÁLIDO!!");
				throw new UsuarioIdMismatchExc("EL USUARIO SEGÚN EL ID " + id + " NO ES VÁLIDO!!");
			} else {
				iUsuarioRepository.delete(iUsuarioRepository.getById(id));
				logger.info("SE HA ELIMINADO CORRECTAMENTE EL USUARIO CON EL ID " + id);
			}

		} catch (Exception e) {
			logger.error("ERROR deleteUsuario : EL USUARIO " + id + " NO SE HA ELIMINADO DE LA DB!!CAUSADO POR "+e);
			throw new UsuarioNotFoundExc("NO SE PUDO ELIMINAR EL USUARIO ", e, false, true);
		}
	}

	// ===================
	// ===== GET ALL =====
	// ===================
	// ------- LISTADO PAGINADO ---------
	public Page<Usuario> getAllUsuario(Pageable pageable) {

		try {
			return iUsuarioRepository.findAll(pageable);
		} catch (Exception e) {

			logger.error("ERROR getAllUsuario : NO SE HAN LISTADO LOS USUARIOS. CAUSADO POR " + e);
			throw new UsuarioNotFoundExc("NO SE PUDO ENCONTRAR EL LISTADO DE USUARIOS ", e);
		}
	}

	// =============== MÉTODOS DE BUSQUEDA ====================

	// ===========================
	// ===== GET BY ID =====
	// ===========================
	// -------USUARIO POR ID---------
	public Optional<Usuario> getById(long id) {

		try {
			return iUsuarioRepository.findById(id);
		} catch (Exception e) {

			logger.error("ERROR getById : NO SE HA ENCONTRADO EL USUARIO SEGUN EL ID " + id + ". CAUSADO POR " + e);
			throw new UsuarioNotFoundExc("NO SE HA ENCONTRADO EL USUARIO SEGUN EL ID INGRESADO ", e);
		}
	}

	// ===========================
	// ===== GET BY NOMBRE =====
	// ===========================
	// -------USUARIO POR NOMBRE---------
	public Page<Usuario> getByNombre(String nombre, Pageable pageable) {

		try {
			return iUsuarioRepository.findByNombre(nombre, pageable);
		} catch (Exception e) {

			logger.error("ERROR getByNombre : NO SE HA ENCONTRADO EL USUARIO SEGUN EL NOMBRE " + nombre
					+ ". CAUSADO POR " + e);
			throw new UsuarioNotFoundExc("NO SE HA ENCONTRADO EL USUARIO SEGUN EL NOMBRE INGRESADO ", e);
		}
	}

	// ===========================
	// ===== GET BY USERNAME =====
	// ===========================
	// -------USUARIO POR USERNAME---------
	public Optional<Usuario> getByUsername(String username) {

		try {
			return iUsuarioRepository.findByUsername(username);
		} catch (Exception e) {

			logger.error("ERROR getByUsername : NO SE HA ENCONTRADO EL USUARIO POR USERNAME. CAUSADO POR " + e);
			throw new UsuarioNotFoundExc("NO SE HA ENCONTRADO EL USUARIO POR USERNAME INGRESADO ", e);
		}
	}

	// ===========================
	// ===== GET BY PASSWORD =====
	// ===========================
	// -------USUARIO POR PASSWORD---------
	public Page<Usuario> getByPassword(String password, Pageable pageable) {

		try {
			return iUsuarioRepository.findByPassword(password, pageable);
		} catch (Exception e) {

			logger.error("ERROR getByPassword : NO SE HA ENCONTRADO EL USUARIO SEGUN EL PASSWORD " + password
					+ ". CAUSADO POR " + e);
			throw new UsuarioNotFoundExc("NO SE HA ENCONTRADO EL USUARIO SEGUN EL PASSWORD INGRESADO ", e);
		}
	}

	// ===========================
	// ===== GET BY EMAIL =====
	// ===========================
	// -------USUARIO POR EMAIL--------
	public Page<Usuario> getByEmail(String email, Pageable pageable) {

		try {
			return iUsuarioRepository.findByEmail(email, pageable);
		} catch (Exception e) {

			logger.error(
					"ERROR getByEmail : NO SE HA ENCONTRADO EL USUARIO SEGUN EL EMAIL " + email + ". CAUSADO POR " + e);
			throw new UsuarioNotFoundExc("NO SE HA ENCONTRADO EL USUARIO SEGUN EL EMAIL INGRESADO ", e);
		}
	}

	// =============== MÉTODOS DE COMPROBACIONES ====================

	// ===========================
	// ===== CHECK BY USERNAME =====
	// ===========================
	public boolean existsByUsername(String username) {
		return iUsuarioRepository.existsByUsername(username);
	}
	
	// ===========================
	// ===== CHECK BY PASSWORD =====
	// ===========================
	public boolean existsByPassword(String password) {
		return iUsuarioRepository.existsByPassword(password);
	}

	// ===========================
	// ===== CHECK BY EMAIL =====
	// ===========================
	public boolean existsByEmail(String email) {
		return iUsuarioRepository.existsByEmail(email);
	}
}