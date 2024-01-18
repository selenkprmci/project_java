package com.FoodOrderingApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @ElementCollection
    @CollectionTable(name = "menu_items", joinColumns = @JoinColumn(name = "menu_id"))
    private List<MenuItem> items; // items alanı MenuItem tipinde

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<MenuItem> getItems() { // Getter metodu MenuItem tipinde
        return items;
    }

    public void setItems(List<MenuItem> items) { // Setter metodu MenuItem tipinde
        this.items = items;
    }
}
