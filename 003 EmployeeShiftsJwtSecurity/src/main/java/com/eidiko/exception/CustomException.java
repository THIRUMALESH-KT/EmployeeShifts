package com.eidiko.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomException  {
	
	  private HttpStatus status;
	  private LocalDateTime timestamp;
	  private String message;
	  private String description;
	  private int statusCode;
	
}
