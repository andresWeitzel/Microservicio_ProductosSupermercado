package com.api.produc.sup.security.exc.usuario;

public class UsuarioIdMismatchExc extends RuntimeException {

	private static final long serialVersionUID = 1L;


	public UsuarioIdMismatchExc() {}

	public UsuarioIdMismatchExc(String msj) {
		super(msj);
	}

	public UsuarioIdMismatchExc(Throwable cause) {
		super(cause);
	}

	public UsuarioIdMismatchExc(String msj, Throwable cause) {
		super(msj, cause);
	}

	
	public UsuarioIdMismatchExc(String msj, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(msj, cause, enableSuppression, writableStackTrace);
	}

}
