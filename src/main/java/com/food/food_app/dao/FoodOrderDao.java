package com.food.food_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.food.food_app.dto.FoodOrder;
import com.food.food_app.repository.FoodOrderRepository;

@Repository
public class FoodOrderDao {
	@Autowired
	private FoodOrderRepository foodOrderRepository;
	
	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {
		return foodOrderRepository.save(foodOrder);
	}

	public FoodOrder updateFoodOrder(FoodOrder foodOrder, int id) {
		if (foodOrderRepository.findById(id).isEmpty()) {
			return null;
		} else {
			foodOrder.setId(id);
			return foodOrderRepository.save(foodOrder);
		}
	}

	public FoodOrder deleteFoodOrder(int id) {
		if (foodOrderRepository.findById(id).isEmpty()) {
			return null;
		} else {
			FoodOrder foodOrder = findFoodOrderById(id).get();
			foodOrderRepository.delete(foodOrder);
			return foodOrder;
		}
	}
	
	public Optional<FoodOrder> findFoodOrderById(int id) {
		return foodOrderRepository.findById(id);
	}
	
	public List<FoodOrder> findAllFoodOrder() {
		return foodOrderRepository.findAll();
	}
	
	public List<FoodOrder> findFoodOrderByPhone(long phone, Sort sort) {
		return foodOrderRepository.findFoodOrderByPhone(phone ,sort);
	}
	
}
