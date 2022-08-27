package com.food.food_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.food.food_app.dao.BranchDao;
import com.food.food_app.dto.Branch;
import com.food.food_app.exception.BranchAlreadyExistException;
import com.food.food_app.exception.BranchNotFoundException;
import com.food.food_app.util.ResponceStructure;

@Service
public class BranchService {

	@Autowired
	BranchDao branchDao;

	// Branch
	public ResponseEntity<ResponceStructure<Branch>> saveBranch(Branch branch) {
		if (branchDao.findBranchByEmail(branch.getEmail()) != null) {
			throw new BranchAlreadyExistException(branch.getEmail());
		}
		ResponceStructure<Branch> structure = new ResponceStructure<Branch>();
		structure.setMessage("Branch Saved Successfully!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(branchDao.saveBranch(branch));
		return new ResponseEntity<ResponceStructure<Branch>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponceStructure<Branch>> updateBranch(Branch branch, int id) {
		if (branchDao.findBranchByEmail(branch.getEmail()) != null) {
			throw new BranchAlreadyExistException(branch.getEmail());
		}
		Branch temp = branchDao.updateBranch(branch, id);
		ResponceStructure<Branch> structure = new ResponceStructure<Branch>();
		if (temp != null) {
			structure.setMessage("Branch Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(temp);
			return new ResponseEntity<ResponceStructure<Branch>>(structure, HttpStatus.OK);
		} else {
			throw new BranchNotFoundException(id);
		}
	}

	public ResponseEntity<ResponceStructure<Branch>> findBranchById(int id) {
		Optional<Branch> branch = branchDao.findBranchById(id);
		if (branch.isEmpty()) {
			throw new BranchNotFoundException(id);
		} else {
			ResponceStructure<Branch> structure = new ResponceStructure<Branch>();
			structure.setMessage("Branch found Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(branch.get());
			return new ResponseEntity<ResponceStructure<Branch>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponceStructure<Branch>> deleteBranch(int id) {
		Branch temp = branchDao.deleteBranch(id);
		ResponceStructure<Branch> structure = new ResponceStructure<Branch>();
		if (temp != null) {
			structure.setMessage("Branch Deleted Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(temp);
			return new ResponseEntity<ResponceStructure<Branch>>(structure, HttpStatus.OK);
		} else {
			throw new BranchNotFoundException(id);
		}
	}

	public ResponseEntity<ResponceStructure<List<Branch>>> findAllBranch() {
		List<Branch> list = branchDao.findAllBranch();
		ResponceStructure<List<Branch>> structure = new ResponceStructure<List<Branch>>();
		if (list.size() == 0) {
			structure.setMessage("No Branch available!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponceStructure<List<Branch>>>(structure, HttpStatus.NOT_FOUND);
		}
		structure.setMessage("Branches found Successfully!");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(list);
		return new ResponseEntity<ResponceStructure<List<Branch>>>(structure, HttpStatus.OK);
	}
}
