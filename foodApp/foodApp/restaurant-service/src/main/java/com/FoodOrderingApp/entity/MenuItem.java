package com.FoodOrderingApp.entity;



import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class MenuItem {
    private String item;
    private Double price;


}
