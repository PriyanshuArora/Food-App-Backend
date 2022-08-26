package com.food.food_app.service;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.food.food_app.dao.BranchDao;
import com.food.food_app.dao.UserDao;
import com.food.food_app.dto.User;
import com.food.food_app.exception.BranchManagerNotFoundException;
import com.food.food_app.exception.BranchNotFoundException;
import com.food.food_app.util.ResponceStructure;

@Service
public class AdminService {

	@Autowired
	BranchDao branchDao;
	@Autowired
	UserDao userDao;
	
	// Admin
	public ResponseEntity<ResponceStructure<List<User>>> findAllBranchManager() {
		List<User> branchManagers = userDao.findUserByRole("Branch Manager");
		ResponceStructure<List<User>> structure = new ResponceStructure<List<User>>();
		if (branchManagers.size() == 0) {
			structure.setMessage("No Branch Managers available!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponceStructure<List<User>>>(structure, HttpStatus.NOT_FOUND);
		}
		structure.setMessage("Branch Managers found Successfully!");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(branchManagers);
		return new ResponseEntity<ResponceStructure<List<User>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponceStructure<List<User>>>findBranchManagerByBranch(int id) {
		if(branchDao.findBranchById(id).isEmpty()) {
			throw new BranchNotFoundException(id);
		}
		List<User> branchManagers = userDao.findUserByBranch(branchDao.findBranchById(id));
		ListIterator<User> listIterator = branchManagers.listIterator();
		while (listIterator.hasNext()) {
			if (!listIterator.next().getRole().contains("Branch Manager")) {
				listIterator.remove();
			}
		}
	
		if (branchManagers.isEmpty()) {
			throw new BranchManagerNotFoundException(id);
		} else {
			ResponceStructure<List<User>> structure = new ResponceStructure<List<User>>();
			structure.setMessage("Branch Managers found for this Branch!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(branchManagers);
			return new ResponseEntity<ResponceStructure<List<User>>>(structure, HttpStatus.OK);
		}
	}
	
}
