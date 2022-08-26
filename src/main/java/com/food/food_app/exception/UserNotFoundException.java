package com.food.food_app.exception;

public class UserNotFoundException extends RuntimeException {
	String message;
	int id;

	public UserNotFoundException(int user_id) {
		id = user_id;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
