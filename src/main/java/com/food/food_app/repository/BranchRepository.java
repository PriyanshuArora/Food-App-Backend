package com.food.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.food_app.dto.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer>{
	public Branch findBranchByEmail(String email);
}