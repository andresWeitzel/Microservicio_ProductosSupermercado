package com.api.rest.productos.supermercado.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.rest.productos.supermercado.exceptions.producto.ProductoIdMismatchException;
import com.api.rest.productos.supermercado.exceptions.producto.ProductoNotFoundException;
import com.api.rest.productos.supermercado.exceptions.usuario.UsuarioIdMismatchException;
import com.api.rest.productos.supermercado.exceptions.usuario.UsuarioNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({ProductoNotFoundException.class})
	protected ResponseEntity<Object> ProductoHandleNotFoundException(Exception ex, WebRequest request){
		
		return handleExceptionInternal(ex, "Producto No Encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ProductoIdMismatchException.class})
	protected ResponseEntity<Object> ProductoIdMismatchException(Exception ex, WebRequest request){
		
		return handleExceptionInternal(ex, "Producto Según su Id No Encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({UsuarioNotFoundException.class})
	protected ResponseEntity<Object> UsuarioHandleNotFoundException(Exception ex, WebRequest request){
		
		return handleExceptionInternal(ex, "Usuario No Encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({UsuarioIdMismatchException.class})
	protected ResponseEntity<Object> UsuarioIdMismatchException(Exception ex, WebRequest request){
		
		return handleExceptionInternal(ex, "Usuario Según su Id No Encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

}
