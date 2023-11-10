package com.eidiko.exception;

import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;


@RestControllerAdvice

public class ExceptionHandeler {
	 @ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
	 public ResponseEntity<Map<String, String>> ExpiredJwtException(io.jsonwebtoken.ExpiredJwtException ex){
			Map<String,String> errorsMap=new HashMap<>();
	    	errorsMap.put("result", "failed");
	    	errorsMap.put("error Message [ "+ex.getClass()+" ]", ex.getLocalizedMessage());
	    	errorsMap.put("statuc",String.valueOf(HttpStatus.BAD_REQUEST.value()));
	    	return new ResponseEntity<Map<String,String>>(errorsMap,HttpStatus.BAD_REQUEST);
	 }
	
	    @ExceptionHandler({MethodArgumentNotValidException.class,})
	   public Map<String,String> validateException(MethodArgumentNotValidException ex){
		 
	    	Map<String,String> errorsMap=new HashMap<>();
	    	errorsMap.put("result", "failed");
	        ex.getBindingResult().getFieldErrors().forEach(error->{
	            errorsMap.put(error.getField(),error.getDefaultMessage());
	        });
	        errorsMap.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
	        return errorsMap;
	    }
	@ExceptionHandler(UserNotFoundException.class) public Map<String,String> methodException(UserNotFoundException ex){
			Map<String,String> errorsMap=new HashMap<>();
	    	errorsMap.put("result", "failed");

	errorsMap.put("errorMessage",ex.getMessage());
    errorsMap.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));

	return  errorsMap;
	    }
	 @ExceptionHandler(HttpMessageNotReadableException.class)
	    public Map<String,String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
	    	Map<String,String> errorsMap=new HashMap<>();
	    	errorsMap.put("result", "failed");

	    	errorsMap.put("error messagee", "Required request body is missing");
	    	errorsMap.put("total message", ex.toString());
	  
	        errorsMap.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));

	        return errorsMap;
	    }
	 @ExceptionHandler(NullPointerException.class)
	 public Map<String,String> NullPinterException(NullPointerException ex){
	    	Map<String,String> errorsMap=new HashMap<>();
	    	errorsMap.put("result", "failed");

	    	errorsMap.put("error message", ex.getLocalizedMessage());
	        errorsMap.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));

	        return errorsMap;
	 }
	 @ExceptionHandler(javax.security.sasl.AuthenticationException.class)
	 public Map<String, String> AuthenticationException(javax.security.sasl.AuthenticationException ex){
			Map<String,String> errorsMap=new HashMap<>();
	    	errorsMap.put("result", "failed");
	    	errorsMap.put("error Message [ "+ex.getClass()+" ]", ex.getLocalizedMessage());
	    	errorsMap.put("statuc",String.valueOf(HttpStatus.BAD_REQUEST.value()));
	    	return errorsMap;
	 }	 @ExceptionHandler(org.springframework.beans.factory.BeanCreationException.class)
	 public Map<String, String>  BeanCreationException(org.springframework.beans.factory.BeanCreationException ex){
			Map<String,String> errorsMap=new HashMap<>();
	    	errorsMap.put("result", "failed");
	    	errorsMap.put("error Messagee [ "+ex.getClass()+" ]", ex.getLocalizedMessage());
	    	errorsMap.put("statuc",String.valueOf(HttpStatus.BAD_REQUEST.value()));
	    	return errorsMap;
	 }	
	 @ExceptionHandler(Exception.class)
	 public Map<String,String> HandleException(Exception ex){
		 Map<String,String> errorsMap=new HashMap<>();
	    	errorsMap.put("result", "failed");

	    	errorsMap.put("error messagee", ex.getLocalizedMessage());
	    	errorsMap.put("total message", ex.toString());
	  
	        errorsMap.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));

	        return errorsMap;
	 }
	 	
}
