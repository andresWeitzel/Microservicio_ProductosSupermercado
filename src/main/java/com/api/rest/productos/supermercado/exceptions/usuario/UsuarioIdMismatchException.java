package com.api.rest.productos.supermercado.exceptions.usuario;

public class UsuarioIdMismatchException extends RuntimeException {

	public UsuarioIdMismatchException() {}

	public UsuarioIdMismatchException(String msj) {
		super(msj);
	}

	public UsuarioIdMismatchException(Throwable cause) {
		super(cause);
	}

	public UsuarioIdMismatchException(String msj, Throwable cause) {
		super(msj, cause);
	}

	
	public UsuarioIdMismatchException(String msj, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(msj, cause, enableSuppression, writableStackTrace);
	}

}
