package com.food.food_app.exception;

public class IdNotFoundException extends RuntimeException {
	String message = "Id not found! enter valid id";
	
	@Override
	public String getMessage() {
		return message;
	}
}
