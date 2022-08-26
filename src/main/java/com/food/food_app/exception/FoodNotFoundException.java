package com.food.food_app.exception;

public class FoodNotFoundException extends RuntimeException {
	String message;
	int id;

	public FoodNotFoundException(int food_id) {
		id = food_id;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
