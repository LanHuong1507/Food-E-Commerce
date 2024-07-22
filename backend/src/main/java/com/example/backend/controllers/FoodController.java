package com.example.backend.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.backend.models.Food;
import com.example.backend.services.FoodService;

@Controller
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService; // Update to FoodService

    private static final String UPLOAD_DIR = "uploads/";

    static {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    // Upload Image
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        try {
            Files.copy(file.getInputStream(), filePath);
            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/foods/images/")
                    .path(fileName)
                    .toUriString();
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to store file");
        }
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR, filename);
        byte[] image = Files.readAllBytes(filePath);
        return ResponseEntity.ok(image);
    }

    @PostMapping
    public ResponseEntity<String> addFood(@RequestBody Food food) { // Update method to handle Food
        foodService.addFood(food); // Update to addFood
        return ResponseEntity.status(HttpStatus.CREATED).body("Food created successfully");
    }

    @GetMapping
    public ResponseEntity<Iterable<Food>> getAllFoods() { // Update method name and return type
        return ResponseEntity.ok(foodService.getAllFoods()); // Update to getAllFoods
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFood(@PathVariable String id, @RequestBody Food food) { // Update method to handle Food
        return foodService.updateFood(id, food); // Update to updateFood
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable String id) { // Update method to handle Food
        return foodService.deleteFood(id); // Update to deleteFood
    }
}
