package com.food.food_app.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.food.food_app.dto.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer>{
	public List<FoodOrder> findFoodOrderByPhone(long phone, Sort sort);
}