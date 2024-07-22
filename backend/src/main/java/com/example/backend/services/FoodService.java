package com.example.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.models.Food;
import com.example.backend.repositories.FoodRepository;
@Service
@Transactional
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public ResponseEntity<Food> getFoodById(String id) {
        Optional<Food> food = foodRepository.findById(id);
        return food.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<String> addFood(Food food) {
        foodRepository.save(food);
        return ResponseEntity.status(HttpStatus.CREATED).body("Food created successfully");
    }

    public ResponseEntity<String> updateFood(String id, Food updatedFood) {
        if (foodRepository.existsById(id)) {
            updatedFood.setId(id);
            foodRepository.save(updatedFood);
            return ResponseEntity.ok("Food updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food not found with id: " + id);
        }
    }

    public ResponseEntity<String> deleteFood(String id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            return ResponseEntity.ok("Food deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food not found with id: " + id);
        }
    }
}
