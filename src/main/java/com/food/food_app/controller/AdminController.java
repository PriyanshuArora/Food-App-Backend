package com.food.food_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.food.food_app.dto.User;
import com.food.food_app.service.AdminService;
import com.food.food_app.util.ResponceStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
		
		@Autowired
		AdminService adminService;

		// Admin
		@GetMapping("/allbranchmanagers")
		public ResponseEntity<ResponceStructure<List<User>>> findAllBranchManagers() {
			return adminService.findAllBranchManager();
		}
		
		@GetMapping("/branchmanagerbybranch/{id}")
		public ResponseEntity<ResponceStructure<List<User>>> findBranchManager(@PathVariable int id) {
			return adminService.findBranchManagerByBranch(id);
		}
		
}

