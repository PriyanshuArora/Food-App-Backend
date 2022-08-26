package com.food.food_app.exception;

public class FoodNotAvailableException extends RuntimeException {
	String message;
	int id;

	public FoodNotAvailableException(int food_id) {
		id = food_id;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
