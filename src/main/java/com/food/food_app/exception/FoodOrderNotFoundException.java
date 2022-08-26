package com.food.food_app.exception;

public class FoodOrderNotFoundException extends RuntimeException {
	String message;
	int id;

	public FoodOrderNotFoundException(int foodOrder_id) {
		id = foodOrder_id;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
