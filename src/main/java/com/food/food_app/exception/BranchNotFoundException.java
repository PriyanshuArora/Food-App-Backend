package com.food.food_app.exception;

public class BranchNotFoundException extends RuntimeException {
	String message;
	int id;

	public BranchNotFoundException(int branch_id) {
		id = branch_id;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
