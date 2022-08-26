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
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.food_app.dto.User;
import com.food.food_app.service.UserService;
import com.food.food_app.util.ResponceStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;

	// User
	@PostMapping("/saveuser")
	public ResponseEntity<ResponceStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/alluser")
	public ResponseEntity<ResponceStructure<List<User>>> findAllUser() {
		return userService.findAllUser();
	}

	@GetMapping("/userbyid/{id}")
	public ResponseEntity<ResponceStructure<User>> findUserById(@PathVariable int id) {
		return userService.findUserById(id);
	}

	@PutMapping("/updateuser")
	public ResponseEntity<ResponceStructure<User>> updateUser(@RequestBody User user, @RequestParam int id) {
		return userService.updateUser(user, id);
	}

	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<ResponceStructure<User>> deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}

	@GetMapping("/login")
	public ResponseEntity<ResponceStructure<User>> findUserByEmail(@RequestParam String email, @RequestParam String password) {
		return userService.loginUser(email, password);
	}
}
