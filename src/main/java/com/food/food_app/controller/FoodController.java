package com.food.food_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.food.food_app.dto.Food;
import com.food.food_app.service.FoodService;
import com.food.food_app.util.ResponceStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FoodController {

	@Autowired
	FoodService foodService;

	// Food
	@PostMapping("/savefood")
	public ResponseEntity<ResponceStructure<Food>> saveFood(@RequestBody Food food) {
		return foodService.saveFood(food);
	}

	@GetMapping("/allfood")
	public ResponseEntity<ResponceStructure<List<Food>>> findAllFood() {
		return foodService.findAllFood();
	}

	@GetMapping("/findfoodbyid/{id}")
	public ResponseEntity<ResponceStructure<Food>> findFoodById(@PathVariable int id) {
		return foodService.findFoodById(id);
	}

	@PutMapping("/updatefood/{id}")
	public ResponseEntity<ResponceStructure<Food>> updateFood(@RequestBody Food food, @PathVariable int id) {
		return foodService.updateFood(food, id);
	}

	@DeleteMapping("/deletefood/{id}")
	public ResponseEntity<ResponceStructure<Food>> deleteFood(@PathVariable int id) {
		return foodService.deleteFood(id);
	}

}
