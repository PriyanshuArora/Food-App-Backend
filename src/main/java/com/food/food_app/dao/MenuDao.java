package com.food.food_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.food.food_app.dto.Menu;
import com.food.food_app.repository.MenuRepository;

@Repository
public class MenuDao {
	@Autowired
	private MenuRepository menuRepository;
	
	public Menu saveMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	public Menu updateMenu(Menu menu, int id) {
		if (menuRepository.findById(id).isEmpty()) {
			return null;
		} else {
			menu.setId(id);
			return menuRepository.save(menu);
		}
	}

	public Menu deleteMenu(int id) {
		if (menuRepository.findById(id).isEmpty()) {
			return null;
		} else {
			Menu menu = findMenuById(id).get();
			menuRepository.delete(menu);
			return menu;
		}
	}
	
	public Optional<Menu> findMenuById(int id) {
		return menuRepository.findById(id);
	}
	
	public List<Menu> findAllMenu() {
		return menuRepository.findAll();
	}
}
