package com.example.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.example.backend.enums.CartStatus;
import com.example.backend.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Document(collection = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private String id;

    @DBRef
    @JsonManagedReference
    private List<CartItem> items;

    private String userId; 

    private double totalCost; 
    
    private LocalDateTime createdAt; 

    private LocalDateTime updatedAt; 
    
    private CartStatus status; 

    private String deliveryAddress;

    private PaymentMethod paymentMethod;

    private String notes;
}