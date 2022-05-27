package com.api.rest.productos.supermercado.security.config.usuario;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioConfiguration {

	private String usuario;

	private String password;

}
