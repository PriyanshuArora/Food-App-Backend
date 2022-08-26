package com.food.food_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.food.food_app.dto.Branch;
import com.food.food_app.dto.User;
import com.food.food_app.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(User user, int id) {
		if (userRepository.findById(id).isEmpty()) {
			return null;
		} else {
			user.setId(id);
			return userRepository.save(user);
		}
	}

	public User deleteUser(int id) {
		if (userRepository.findById(id).isEmpty()) {
			return null;
		} else {
			User user = findUserById(id).get();
			userRepository.delete(user);
			return user;
		}
	}
	
	public Optional<User> findUserById(int id) {
		return userRepository.findById(id);
	}
	
	public List<User> findAllUser() {
		return userRepository.findAll();
	}
	
	public List<User> findUserByRole(String role) {
		return userRepository.findUserByRole(role);
	}
	
	public List<User> findUserByBranch(Optional<Branch> branch) {
		return userRepository.findUserByBranch(branch);
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}
}
