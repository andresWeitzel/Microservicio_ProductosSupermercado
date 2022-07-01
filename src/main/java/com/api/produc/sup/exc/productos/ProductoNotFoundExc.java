package com.api.produc.sup.exc.productos;

public class ProductoNotFoundExc extends RuntimeException{
	

	public ProductoNotFoundExc() {}

	public ProductoNotFoundExc(String msj) {
		super(msj);
	}

	
	public ProductoNotFoundExc(Throwable cause) {
		super(cause);
	}
	
	public ProductoNotFoundExc(String msj,Throwable cause) {
		super(msj,cause);
	}

	
	public ProductoNotFoundExc(String msj,Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(msj,cause, enableSuppression, writableStackTrace);
	}


}
