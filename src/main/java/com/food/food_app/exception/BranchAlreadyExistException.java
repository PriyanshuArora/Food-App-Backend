package com.food.food_app.exception;

public class BranchAlreadyExistException extends RuntimeException {
	String message;
	String email;

	public BranchAlreadyExistException(String email) {
		this.email = email;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
