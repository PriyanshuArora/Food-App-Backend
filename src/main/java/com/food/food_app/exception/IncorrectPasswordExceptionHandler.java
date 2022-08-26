package com.food.food_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.food.food_app.util.ResponceStructure;

@ControllerAdvice
public class IncorrectPasswordExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<ResponceStructure<String>> IncorrectPasswordException(IncorrectPasswordException exception) {
		ResponceStructure<String> structure = new ResponceStructure<String>();
		structure.setMessage("Incorrect Password!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("Please enter correct password.");
		
		return new ResponseEntity<ResponceStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
