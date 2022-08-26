package com.food.food_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.food.food_app.dto.Food;
import com.food.food_app.repository.FoodRepository;

@Repository
public class FoodDao {
	@Autowired
	private FoodRepository foodRepository;
	
	public Food saveFood(Food food) {
		return foodRepository.save(food);
	}

	public Food updateFood(Food food, int id) {
		if (foodRepository.findById(id).isEmpty()) {
			return null;
		} else {
			food.setId(id);
			return foodRepository.save(food);
		}
	}

	public Food deleteFood(int id) {
		if (foodRepository.findById(id).isEmpty()) {
			return null;
		} else {
			Food food = findFoodById(id).get();
			foodRepository.delete(food);
			return food;
		}
	}
	
	public Optional<Food> findFoodById(int id) {
		return foodRepository.findById(id);
	}
	
	public List<Food> findAllFood() {
		return foodRepository.findAll();
	}
}
