package com.api.rest.productos.supermercado.exceptions.usuario;

public class UsuarioNotFoundException extends RuntimeException{

	
	public UsuarioNotFoundException() {}

	public UsuarioNotFoundException(String msj) {
		super(msj);
	}

	
	public UsuarioNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public UsuarioNotFoundException(String msj,Throwable cause) {
		super(msj,cause);
	}

	
	public UsuarioNotFoundException(String msj,Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(msj,cause, enableSuppression, writableStackTrace);
	}
	
	
	
}
