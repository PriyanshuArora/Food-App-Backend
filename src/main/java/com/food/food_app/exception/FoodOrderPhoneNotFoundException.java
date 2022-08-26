package com.food.food_app.exception;

public class FoodOrderPhoneNotFoundException extends RuntimeException {
	String message;
	long phone;

	public FoodOrderPhoneNotFoundException(long foodOrder_phone) {
		phone = foodOrder_phone;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
