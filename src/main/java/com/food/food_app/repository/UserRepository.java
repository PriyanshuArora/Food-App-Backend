package com.food.food_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.food_app.dto.Branch;
import com.food.food_app.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public List<User> findUserByRole(String role);
	public List<User> findUserByBranch(Optional<Branch> branch);
	public User findUserByEmail(String email);
}