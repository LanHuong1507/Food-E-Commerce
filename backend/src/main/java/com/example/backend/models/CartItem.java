package com.example.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "cartItems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    private String id;
    
    private Food food;
    private int quantity;

    @DBRef
    @JsonBackReference
    private Cart cart; // Ensure this is properly defined

    public double getDiscountedPrice() {
        return food.getDiscountedPrice();
    }

    public double calculateTotalPrice() {
        return getDiscountedPrice() * quantity;
    }
}
