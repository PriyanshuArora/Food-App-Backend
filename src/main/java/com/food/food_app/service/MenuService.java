package com.food.food_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.food.food_app.dao.FoodDao;
import com.food.food_app.dao.MenuDao;
import com.food.food_app.dto.Food;
import com.food.food_app.dto.Menu;
import com.food.food_app.exception.FoodNotAvailableException;
import com.food.food_app.exception.FoodNotFoundException;
import com.food.food_app.exception.MenuNotFoundException;
import com.food.food_app.util.ResponceStructure;

@Service
public class MenuService {

	@Autowired
	MenuDao menuDao;
	@Autowired
	FoodDao foodDao;

	// Menu
	public ResponseEntity<ResponceStructure<Menu>> saveMenu(Menu menu) {
		List<Food> foods = menu.getFoods();
		for (Food i : foods) {
			Optional<Food> food = foodDao.findFoodById(i.getId());
			if(food.isEmpty()) {
				throw new FoodNotFoundException(i.getId());
			}
			else if (!food.get().isAvailability()) {
				throw new FoodNotAvailableException(i.getId());
			}
		}
		
		ResponceStructure<Menu> structure = new ResponceStructure<Menu>();
		structure.setMessage("Menu Saved Successfully!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(menuDao.saveMenu(menu));
		return new ResponseEntity<ResponceStructure<Menu>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponceStructure<Menu>> updateMenu(Menu menu, int id) {
		List<Food> foods = menu.getFoods();
		for (Food i : foods) {
			Optional<Food> temp = foodDao.findFoodById(i.getId());
			Optional<Food> food = foodDao.findFoodById(i.getId());
			if(food.isEmpty()) {
				throw new FoodNotFoundException(i.getId());
			}
			else if (!temp.get().isAvailability()) {
				throw new FoodNotAvailableException(i.getId());
			}
		}
		
		Menu temp = menuDao.updateMenu(menu, id);
		ResponceStructure<Menu> structure = new ResponceStructure<Menu>();
		if (temp != null) {
			structure.setMessage("Menu Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(temp);
			return new ResponseEntity<ResponceStructure<Menu>>(structure, HttpStatus.OK);
		} else {
			throw new MenuNotFoundException(id);
		}
	}

	public ResponseEntity<ResponceStructure<Menu>> findMenuById(int id) {
		Optional<Menu> optional = menuDao.findMenuById(id);
		if (optional.isEmpty()) {
			throw new MenuNotFoundException(id);
		} else {
			ResponceStructure<Menu> structure = new ResponceStructure<Menu>();
			structure.setMessage("Menu found Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponceStructure<Menu>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponceStructure<Menu>> deleteMenu(int id) {
		Menu temp = menuDao.deleteMenu(id);
		ResponceStructure<Menu> structure = new ResponceStructure<Menu>();
		if (temp != null) {
			structure.setMessage("Menu Deleted Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(temp);
			return new ResponseEntity<ResponceStructure<Menu>>(structure, HttpStatus.OK);
		} else {
			throw new MenuNotFoundException(id);
		}
	}

	public ResponseEntity<ResponceStructure<List<Menu>>> findAllMenu() {
		List<Menu> list = menuDao.findAllMenu();
		ResponceStructure<List<Menu>> structure = new ResponceStructure<List<Menu>>();
		if (list.size() == 0) {
			structure.setMessage("No Menu available!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponceStructure<List<Menu>>>(structure, HttpStatus.NOT_FOUND);
		}
		structure.setMessage("Menu found Successfully!");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(list);
		return new ResponseEntity<ResponceStructure<List<Menu>>>(structure, HttpStatus.OK);
	}
}
