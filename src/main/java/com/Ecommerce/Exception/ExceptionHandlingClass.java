package com.Ecommerce.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingClass {
	
@ExceptionHandler(value=InvalidEntry.class)
	public ResponseEntity<Object> exception (InvalidEntry msg)
	{
		return new  ResponseEntity<>(msg.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
}
