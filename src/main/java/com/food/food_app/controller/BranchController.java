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

import com.food.food_app.dto.Branch;
import com.food.food_app.service.BranchService;
import com.food.food_app.util.ResponceStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BranchController {

	@Autowired
	BranchService branchService;

	// Branch
	@PostMapping("/savebranch")
	public ResponseEntity<ResponceStructure<Branch>> saveBranch(@RequestBody Branch branch) {
		return branchService.saveBranch(branch);
	}

	@GetMapping("/allbranch")
	public ResponseEntity<ResponceStructure<List<Branch>>> findAllBranch() {
		return branchService.findAllBranch();
	}

	@GetMapping("/findbranchbyid/{id}")
	public ResponseEntity<ResponceStructure<Branch>> findBranchById(@PathVariable int id) {
		return branchService.findBranchById(id);
	}

	@PutMapping("/updatebranch/{id}")
	public ResponseEntity<ResponceStructure<Branch>> updateBranch(@RequestBody Branch branch, @PathVariable int id) {
		return branchService.updateBranch(branch, id);
	}

	@DeleteMapping("/deletebranch/{id}")
	public ResponseEntity<ResponceStructure<Branch>> deleteBranch(@PathVariable int id) {
		return branchService.deleteBranch(id);
	}

}
