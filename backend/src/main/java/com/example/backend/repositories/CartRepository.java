package com.example.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend.models.Cart;



public interface CartRepository extends MongoRepository<Cart, String> {
    
}
