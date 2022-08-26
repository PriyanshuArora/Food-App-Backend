package com.food.food_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.food.food_app.dao.FoodDao;
import com.food.food_app.dto.Food;
import com.food.food_app.exception.FoodNotAvailableException;
import com.food.food_app.util.ResponceStructure;

@Service
public class FoodService {

	@Autowired
	FoodDao foodDao;
	
	// Food
	public ResponseEntity<ResponceStructure<Food>> saveFood(Food food) {
		ResponceStructure<Food> structure = new ResponceStructure<Food>();
		structure.setMessage("Food Saved Successfully!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(foodDao.saveFood(food));
		return new ResponseEntity<ResponceStructure<Food>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponceStructure<Food>> updateFood(Food food, int id) {
		Food temp = foodDao.updateFood(food, id);
		ResponceStructure<Food> structure = new ResponceStructure<Food>();
		if (temp != null) {
			structure.setMessage("Food Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(temp);
			return new ResponseEntity<ResponceStructure<Food>>(structure, HttpStatus.OK);
		} else {
			throw new FoodNotAvailableException(id);
		}
	}
	
	public ResponseEntity<ResponceStructure<Food>> findFoodById(int id) {
		Optional<Food> optional = foodDao.findFoodById(id);
		if (optional.isEmpty()) {
			throw new FoodNotAvailableException(id);
		} else {
			ResponceStructure<Food> structure = new ResponceStructure<Food>();
			structure.setMessage("Food found Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponceStructure<Food>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponceStructure<Food>> deleteFood(int id) {
		Food temp = foodDao.deleteFood(id);
		ResponceStructure<Food> structure = new ResponceStructure<Food>();
		if (temp != null) {
			structure.setMessage("Food Deleted Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(temp);
			return new ResponseEntity<ResponceStructure<Food>>(structure, HttpStatus.OK);
		} else {
			throw new FoodNotAvailableException(id);
		}
	}

	public ResponseEntity<ResponceStructure<List<Food>>> findAllFood() {
		List<Food> list = foodDao.findAllFood();
		ResponceStructure<List<Food>> structure = new ResponceStructure<List<Food>>();
		if (list.size() == 0) {
			structure.setMessage("No Food available!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponceStructure<List<Food>>>(structure, HttpStatus.NOT_FOUND);
		}
		structure.setMessage("Foods found Successfully!");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(list);
		return new ResponseEntity<ResponceStructure<List<Food>>>(structure, HttpStatus.OK);
	}
}
