package com.api.produc.sup.security.exc.usuario;

public class UsuarioNotFoundExc extends RuntimeException{

	
	private static final long serialVersionUID = 1L;


	public UsuarioNotFoundExc() {}

	public UsuarioNotFoundExc(String msj) {
		super(msj);
	}

	
	public UsuarioNotFoundExc(Throwable cause) {
		super(cause);
	}
	
	public UsuarioNotFoundExc(String msj,Throwable cause) {
		super(msj,cause);
	}

	
	public UsuarioNotFoundExc(String msj,Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(msj,cause, enableSuppression, writableStackTrace);
	}
	
	
	
}
