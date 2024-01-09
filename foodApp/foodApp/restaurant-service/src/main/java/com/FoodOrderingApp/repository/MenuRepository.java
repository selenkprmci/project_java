package com.FoodOrderingApp.repository;

import com.FoodOrderingApp.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    // Burada restorana özel menü sorgulama metotları gibi custom sorgularınızı ekleyebilirsiniz.
    // Örneğin:
    // List<Menu> findByRestaurantId(Long restaurantId);
}

