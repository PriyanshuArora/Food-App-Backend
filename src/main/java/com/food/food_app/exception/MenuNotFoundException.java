package com.food.food_app.exception;

public class MenuNotFoundException extends RuntimeException {
	String message;
	int id;

	public MenuNotFoundException(int menu_id) {
		id = menu_id;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
