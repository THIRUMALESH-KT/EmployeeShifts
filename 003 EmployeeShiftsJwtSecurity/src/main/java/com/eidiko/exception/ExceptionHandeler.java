package com.eidiko.exception;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.RepaintManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandeler  {
	 @ExceptionHandler(HttpClientErrorException.class)
	    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
	        // Extract the error response from the exception
	     log.info("inside ********* handleHttpClientErrorException");  
	       Map<String , Object>  obj= ex.getResponseBodyAs(LinkedHashMap.class);
	     return ResponseEntity.badRequest().body(obj);

	    }


	
    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        log.info("********** inside ExpiredJwtException");

        Map<String , Object> map=new LinkedHashMap<>();
    	map.put("message ","Failed");
    	map.put("result ", ex.getLocalizedMessage());
    	map.put("status" , HttpStatus.BAD_REQUEST);
    	map.put("statusCode", HttpStatus.BAD_REQUEST.value());
    	return new  ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {SignatureException.class})
    public ResponseEntity<Map<String, Object>> JwtException(SignatureException ex, WebRequest request) {
   	 log.info("********** inside SignatureException");

   	 Map<String , Object> map=new LinkedHashMap<>();
 	map.put("message ","Failed");
 	map.put("result ", ex.getLocalizedMessage());
 	map.put("status" , HttpStatus.BAD_REQUEST);
 	map.put("statusCode", HttpStatus.BAD_REQUEST.value());
 	return new  ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		}
    @ExceptionHandler(value = {MalformedJwtException.class})
    public ResponseEntity<Map<String, Object>> MalformedJwtException(MalformedJwtException ex, WebRequest request) {
   	 log.info("********** inside MalformedJwtException");
   	 Map<String , Object> map=new LinkedHashMap<>();
 	map.put("message ","Failed");
 	map.put("result ", ex.getLocalizedMessage());
 	map.put("status" , HttpStatus.BAD_REQUEST);
 	map.put("statusCode", HttpStatus.BAD_REQUEST.value());
 	return new  ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		}
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String , Object>> Exception(Exception ex){
   	 log.info("********** inside Exception");

    	Map<String , Object> map=new LinkedHashMap<>();
    	map.put("message ","Failed");
    	map.put("result ", ex.getLocalizedMessage());
    	map.put("status" , HttpStatus.BAD_REQUEST);
    	map.put("statusCode", HttpStatus.BAD_REQUEST.value());
    	return new  ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
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
	
	 	
}
