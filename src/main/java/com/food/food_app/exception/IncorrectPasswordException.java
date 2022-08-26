package com.food.food_app.exception;

public class IncorrectPasswordException extends RuntimeException {
	String message;
	
	@Override
	public String getMessage() {
		return message;
	}
}
