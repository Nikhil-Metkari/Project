package com.Ecommerce.Exception;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.Ecommerce.Category.Model.Category;

public class InvalidEntry extends RuntimeException{

	public InvalidEntry (String exception) {
		super (exception); 
	}

	
}
