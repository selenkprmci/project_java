package com.FoodOrderingApp.repository;

import com.FoodOrderingApp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Bu, genel bir JpaRepository'dir ve temel CRUD operasyonlarını sağlar.
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Burada custom query'lerinizi tanımlayabilirsiniz, örneğin:
    // List<Restaurant> findByNameContaining(String name);
}