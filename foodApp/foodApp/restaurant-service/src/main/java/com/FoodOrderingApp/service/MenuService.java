package com.FoodOrderingApp.service;



import com.FoodOrderingApp.entity.Menu;
import com.FoodOrderingApp.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
    }

    public Menu updateMenu(Long id, Menu menuDetails) {
        Menu menu = getMenuById(id);
        menu.setItems(menuDetails.getItems());
        // Update other fields as necessary
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        Menu menu = getMenuById(id);
        menuRepository.delete(menu);
    }
}

