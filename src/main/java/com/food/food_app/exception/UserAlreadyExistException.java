package com.food.food_app.exception;

public class UserAlreadyExistException extends RuntimeException {
	String message;
	String email;

	public UserAlreadyExistException(String email) {
		this.email = email;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
