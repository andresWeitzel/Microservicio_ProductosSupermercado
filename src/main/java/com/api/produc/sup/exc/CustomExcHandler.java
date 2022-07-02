package com.api.produc.sup.exc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.produc.sup.exc.productos.ProductoIdMismatchExc;
import com.api.produc.sup.exc.productos.ProductoNotFoundExc;
//import com.api.produc.sup.exc.productos.ProductoIdMismatchExc;
//import com.api.produc.sup.exc.productos.ProductoNotFoundExc;
import com.api.produc.sup.security.exc.usuario.UsuarioIdMismatchExc;
import com.api.produc.sup.security.exc.usuario.UsuarioNotFoundExc;

@RestControllerAdvice
public class CustomExcHandler extends ResponseEntityExceptionHandler{
	

	@ExceptionHandler({ProductoNotFoundExc.class})
	protected ResponseEntity<Object> ProductoHandleNotFoundException(Exception ex, WebRequest request){
		
		return handleExceptionInternal(ex, "Producto No Encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ProductoIdMismatchExc.class})
	protected ResponseEntity<Object> ProductoIdMismatchException(Exception ex, WebRequest request){
		
		return handleExceptionInternal(ex, "Producto Según su Id No Encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({UsuarioNotFoundExc.class})
	protected ResponseEntity<Object> UsuarioHandleNotFoundException(Exception ex, WebRequest request){
		
		return handleExceptionInternal(ex, "Usuario No Encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({UsuarioIdMismatchExc.class})
	protected ResponseEntity<Object> UsuarioIdMismatchException(Exception ex, WebRequest request){
		
		return handleExceptionInternal(ex, "Usuario Según su Id No Encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	

}
