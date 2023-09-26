package com.eidiko.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandeler {
	
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

	    	errorsMap.put("error message", "Required request body is missing");
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

}
