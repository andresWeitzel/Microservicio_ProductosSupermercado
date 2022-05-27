package com.api.rest.productos.supermercado.services;

import java.util.UUID;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.rest.productos.supermercado.entities.Usuario;
import com.api.rest.productos.supermercado.exceptions.usuario.UsuarioIdMismatchException;
import com.api.rest.productos.supermercado.exceptions.usuario.UsuarioNotFoundException;
import com.api.rest.productos.supermercado.repositories.I_UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private I_UsuarioRepository iUsuarioRepository;
	
	// =============== LOGS ====================
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(UsuarioService.class);


	// =============== MÉTODOS CRUD ====================

		// ==================
		// ===== INSERT =====
		// ==================
		public void addUsuario(Usuario usuario) {
			try {
				if (usuario == null) {
					logger.error("ERROR addUsuario : EL USUARIO " + usuario + " ES NULO!!");
					throw new UsuarioNotFoundException("EL USUARIO ES NULO");
				} else {
					iUsuarioRepository.save(usuario);
				}

			} catch (Exception e) {
				logger.error("ERROR addUsuario : EL USUARIO " + usuario + " NO SE HA INSERTADO EN LA DB!!");
				throw new UsuarioNotFoundException("NO SE PUDO AGREGAR EL USUARIO ", e, false, true);
			}
		}
		
		
		// ==================
		// ===== UPDATE =====
		// ==================
		public void updateUsuario(Usuario usuario) {
			try {
				if (usuario == null) {
					logger.error("ERROR updateUsuario : EL USUARIO " + usuario + " ES NULO!!");
					throw new UsuarioNotFoundException("EL USUARIO ES NULO");
				} else {
					iUsuarioRepository.save(usuario);
					
				}

			} catch (Exception e) {
				logger.error("ERROR updateUsuario : EL USUARIO " + usuario + " NO SE HA ACTUALIZADO EN LA DB!!");
				throw new UsuarioNotFoundException("NO SE PUDO ACTUALIZAR EL USUARIO ", e, false, true);
			}
		}

		
		
		// ==================
		// ===== DELETE =====
		// ==================
		public void deleteUsuario(UUID id) {
			try {
				if (id == null) {
					logger.error("ERROR deleteUsuario : EL USUARIO " + id + " NO ES VÁLIDO!!");
					throw new UsuarioIdMismatchException("EL USUARIO SEGÚN EL ID "+id+" NO ES VÁLIDO!!");
				} else {
					iUsuarioRepository.delete(iUsuarioRepository.getById(id));
					logger.info("SE HA ELIMINADO CORRECTAMENTE EL USUARIO CON EL ID " + id);
				}

			} catch (Exception e) {
				logger.error("ERROR deleteUsuario : EL USUARIO " + id + " NO SE HA ELIMINADO DE LA DB!!");
				throw new UsuarioNotFoundException("NO SE PUDO ELIMINAR EL USUARIO ", e, false, true);
			}
		}
		
		// ===================
		// ===== GET ALL =====
		// ===================
		// ------- LISTADO PAGINADO ---------
		public Page<Usuario> findAllUsuario(Pageable pageable) {

			try {
				return iUsuarioRepository.findAll(pageable);
			} catch (Exception e) {

				logger.error("ERROR getAllUsuario : NO SE HAN LISTADO LOS USUARIOS. CAUSADO POR " + e);
				throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL LISTADO DE USUARIOS ", e);
			}
		}

}
