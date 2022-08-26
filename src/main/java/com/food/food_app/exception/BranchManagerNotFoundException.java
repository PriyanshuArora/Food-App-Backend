package com.food.food_app.exception;

public class BranchManagerNotFoundException extends RuntimeException {
	String message;
	int id;

	public BranchManagerNotFoundException(int branch_id) {
		id = branch_id;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
