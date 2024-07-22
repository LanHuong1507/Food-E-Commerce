package com.example.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend.models.Food;

public interface FoodRepository extends MongoRepository<Food, String> {
    
}
