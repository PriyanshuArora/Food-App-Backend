package com.food.food_app.exception;

public class EmailNotFoundException extends RuntimeException {
	String message;
	String email;

	public EmailNotFoundException(String email) {
		this.email = email;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
