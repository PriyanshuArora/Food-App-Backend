package com.food.food_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.food.food_app.util.ResponceStructure;

@ControllerAdvice
public class IdNotFoundExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> idNotFoundException(IdNotFoundException exception) {
		ResponceStructure<String> structure = new ResponceStructure<String>();
		structure.setMessage("Id Not Found in Database");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("No such Id Found");
		
		return new ResponseEntity<ResponceStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
