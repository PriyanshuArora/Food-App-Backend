package com.food.food_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.food.food_app.dao.BranchDao;
import com.food.food_app.dao.UserDao;
import com.food.food_app.dto.User;
import com.food.food_app.exception.BranchNotFoundException;
import com.food.food_app.exception.EmailNotFoundException;
import com.food.food_app.exception.IncorrectPasswordException;
import com.food.food_app.exception.UserAlreadyExistException;
import com.food.food_app.exception.UserNotFoundException;
import com.food.food_app.util.AES;
import com.food.food_app.util.ResponceStructure;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	AES aes;
	@Autowired
	BranchDao branchDao;

	// User
	public ResponseEntity<ResponceStructure<User>> saveUser(User user) {
		if (userDao.findUserByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistException(user.getEmail());
		} else if (!user.getRole().contains("Admin")) {
			if (branchDao.findBranchById(user.getBranch().getId()).isEmpty()) {
				throw new BranchNotFoundException(user.getBranch().getId());
			}
		} else if (user.getRole().contains("Admin")) {
			user.setBranch(null);
		} 
		
		user.setPassword(aes.encrypt(user.getPassword(), "secure"));
		ResponceStructure<User> structure = new ResponceStructure<User>();
		structure.setMessage("User Saved Successfully!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(userDao.saveUser(user));
		return new ResponseEntity<ResponceStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponceStructure<User>> updateUser(User user, int id) {
		if (userDao.findUserByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistException(user.getEmail());
		} else if (!user.getRole().contains("Admin")) {
			if (branchDao.findBranchById(user.getBranch().getId()).isEmpty()) {
				throw new BranchNotFoundException(user.getBranch().getId());
			}
		} else if (user.getRole().contains("Admin")) {
			user.setBranch(null);
		} 
		
		user.setPassword(aes.encrypt(user.getPassword(), "secure"));
		User temp = userDao.updateUser(user, id);
		ResponceStructure<User> structure = new ResponceStructure<User>();
		if (temp != null) {
			if (branchDao.findBranchById(user.getBranch().getId()).isEmpty()) {
				throw new BranchNotFoundException(user.getBranch().getId());
			}
			user.setPassword(aes.encrypt(user.getPassword(), "secure"));
			structure.setMessage("User Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(temp);
			return new ResponseEntity<ResponceStructure<User>>(structure, HttpStatus.OK);
		} else {
			throw new UserNotFoundException(id);
		}
	}

	public ResponseEntity<ResponceStructure<User>> findUserById(int id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty()) {
			throw new UserNotFoundException(id);
		} else {
			ResponceStructure<User> structure = new ResponceStructure<User>();
			structure.setMessage("User found Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponceStructure<User>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponceStructure<User>> deleteUser(int id) {
		User temp = userDao.deleteUser(id);
		ResponceStructure<User> structure = new ResponceStructure<User>();
		if (temp != null) {
			structure.setMessage("User Deleted Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(temp);
			return new ResponseEntity<ResponceStructure<User>>(structure, HttpStatus.OK);
		} else {
			throw new UserNotFoundException(id);
		}
	}

	public ResponseEntity<ResponceStructure<List<User>>> findAllUser() {
		List<User> list = userDao.findAllUser();
		ResponceStructure<List<User>> structure = new ResponceStructure<List<User>>();
		if (list.size() == 0) {
			structure.setMessage("No User available!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponceStructure<List<User>>>(structure, HttpStatus.NOT_FOUND);
		}
		structure.setMessage("Users found Successfully!");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(list);
		return new ResponseEntity<ResponceStructure<List<User>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponceStructure<User>> loginUser(String email, String password) {
		User user = userDao.findUserByEmail(email);
		if (user == null) {
			throw new EmailNotFoundException(email);
		} else if (!aes.encrypt(password, "secure").contains(user.getPassword())) {
			throw new IncorrectPasswordException();
		}
		user.setPassword(null);
		ResponceStructure<User> structure = new ResponceStructure<User>();
		structure.setMessage("User found Successfully!");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(user);
		return new ResponseEntity<ResponceStructure<User>>(structure, HttpStatus.OK);
	}
}
