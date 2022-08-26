package com.food.food_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.food.food_app.dto.Branch;
import com.food.food_app.repository.BranchRepository;

@Repository
public class BranchDao {
	@Autowired
	private BranchRepository branchRepository;
	
	public Branch saveBranch(Branch branch) {
		return branchRepository.save(branch);
	}
	
	public Branch updateBranch(Branch branch, int id) {
		if (branchRepository.findById(id).isEmpty()) {
			return null;
		} else {
			branch.setId(id);
			return branchRepository.save(branch);
		}
	}

	public Branch deleteBranch(int id) {
		if (branchRepository.findById(id).isEmpty()) {
			return null;
		} else {
			Branch branch = findBranchById(id).get();
			branchRepository.delete(branch);
			return branch;
		}
	}
	
	public Optional<Branch> findBranchById(int id) {
		return branchRepository.findById(id);
	}
	
	public List<Branch> findAllBranch() {
		return branchRepository.findAll();
	}
	
	public Branch findBranchByEmail(String email) {
		return branchRepository.findBranchByEmail(email);
	}
}
