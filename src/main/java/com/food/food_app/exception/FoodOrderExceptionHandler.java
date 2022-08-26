package com.food.food_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.food.food_app.util.ResponceStructure;

@ControllerAdvice
public class FoodOrderExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(FoodOrderNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> FoodOrderNotFoundException(FoodOrderNotFoundException exception) {
		ResponceStructure<String> structure = new ResponceStructure<String>();
		structure.setMessage("Food Order with id:" + exception.id +" does not exist.");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setT("Food Order not found");
		
		return new ResponseEntity<ResponceStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
