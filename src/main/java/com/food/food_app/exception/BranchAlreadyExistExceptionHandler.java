package com.food.food_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.food.food_app.util.ResponceStructure;

@ControllerAdvice
public class BranchAlreadyExistExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(BranchAlreadyExistException.class)
	public ResponseEntity<ResponceStructure<String>> BranchAlreadyExistException(BranchAlreadyExistException exception) {
		ResponceStructure<String> structure = new ResponceStructure<String>();
		structure.setMessage("Branch with email:" + exception.email + " already exists.");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("Enter other email.");
		
		return new ResponseEntity<ResponceStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
