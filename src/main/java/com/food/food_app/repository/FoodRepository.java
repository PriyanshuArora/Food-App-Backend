package com.food.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.food_app.dto.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>{
	
}