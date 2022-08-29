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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.food_app.dto.FoodOrder;
import com.food.food_app.service.FoodOrderService;
import com.food.food_app.util.ResponceStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FoodOrderController {

	@Autowired
	FoodOrderService foodOrderService;

	// FoodOrder
	@PostMapping("/savefoodorder")
	public ResponseEntity<ResponceStructure<FoodOrder>> saveFoodOrder(@RequestBody FoodOrder foodOrder) {
		return foodOrderService.saveFoodOrder(foodOrder);
	}

	@GetMapping("/allfoodorder")
	public ResponseEntity<ResponceStructure<List<FoodOrder>>> findAllFoodOrder() {
		return foodOrderService.findAllFoodOrder();
	}

	@GetMapping("/findfoodorderbyid/{id}")
	public ResponseEntity<ResponceStructure<FoodOrder>> findFoodOrderById(@PathVariable int id) {
		return foodOrderService.findFoodOrderById(id);
	}

	@PutMapping("/updatefoodorder/{id}")
	public ResponseEntity<ResponceStructure<FoodOrder>> updateFoodOrder(@RequestBody FoodOrder foodOrder, @PathVariable int id) {
		return foodOrderService.updateFoodOrder(foodOrder, id);
	}

	@DeleteMapping("/deletefoodorder/{id}")
	public ResponseEntity<ResponceStructure<FoodOrder>> deleteFoodOrder(@PathVariable int id) {
		return foodOrderService.deleteFoodOrder(id);
	}
	
	@GetMapping("/billusingphone")
	public ResponseEntity<ResponceStructure<String>> billByPhone(@RequestParam long phone) {
		return foodOrderService.findBillByPhone(phone);
	}
	
	@GetMapping("/billusingid")
	public ResponseEntity<ResponceStructure<String>> billById(@RequestParam int id, @RequestParam String email) {
		return foodOrderService.findBillByOrderId(id, email);
	}
}
