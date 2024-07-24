package com.example.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend.models.CartItem;

public interface CartItemRepository extends MongoRepository<CartItem, String> {
    
}
