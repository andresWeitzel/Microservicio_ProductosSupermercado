


package com.api.produc.sup.exc.productos;

public class ProductoIdMismatchExc extends RuntimeException {

	public ProductoIdMismatchExc() {}

	public ProductoIdMismatchExc(String msj) {
		super(msj);
	}

	public ProductoIdMismatchExc(Throwable cause) {
		super(cause);
	}

	public ProductoIdMismatchExc(String msj, Throwable cause) {
		super(msj, cause);
	}

	
	public ProductoIdMismatchExc(String msj, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(msj, cause, enableSuppression, writableStackTrace);
	}

}

