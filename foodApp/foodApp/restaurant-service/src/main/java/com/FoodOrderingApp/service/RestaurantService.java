package com.FoodOrderingApp.service;


import com.FoodOrderingApp.entity.Restaurant;
import com.FoodOrderingApp.exception.ResourceNotFoundException;
import com.FoodOrderingApp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant createRestaurant(Restaurant restaurant) {
        // Restoran oluşturma iş mantığı burada yer alır
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        // Tüm restoranları döndürme iş mantığı burada yer alır
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        // Restoran detaylarını getirme iş mantığı burada yer alır
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
    }

    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) {
        // Restoran güncelleme iş mantığı burada yer alır
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setName(restaurantDetails.getName());
        restaurant.setLocation(restaurantDetails.getLocation());
        // Diğer alanların güncellenmesi
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        // Restoran silme iş mantığı burada yer alır
        Restaurant restaurant = getRestaurantById(id);
        restaurantRepository.delete(restaurant);
    }
}
