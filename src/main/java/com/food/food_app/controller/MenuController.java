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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.food_app.dto.Menu;
import com.food.food_app.service.MenuService;
import com.food.food_app.util.ResponceStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MenuController {
	@Autowired
	MenuService menuService;

	// Menu
	@PostMapping("/savemenu")
	public ResponseEntity<ResponceStructure<Menu>> saveMenu(@RequestBody Menu menu) {
		return menuService.saveMenu(menu);
	}

	@GetMapping("/allmenu")
	public ResponseEntity<ResponceStructure<List<Menu>>> findAllMenu() {
		return menuService.findAllMenu();
	}

	@GetMapping("/menubyid/{id}")
	public ResponseEntity<ResponceStructure<Menu>> findMenuById(@PathVariable int id) {
		return menuService.findMenuById(id);
	}

	@PutMapping("/updatemenu")
	public ResponseEntity<ResponceStructure<Menu>> updateMenu(@RequestBody Menu menu, @RequestParam int id) {
		return menuService.updateMenu(menu, id);
	}

	@DeleteMapping("/deletemenu")
	public ResponseEntity<ResponceStructure<Menu>> deleteMenu(@RequestParam int id) {
		return menuService.deleteMenu(id);
	}

}
