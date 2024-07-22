package com.example.backend.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.backend.enums.Brand;
import com.example.backend.enums.Size;
import com.example.backend.enums.FoodCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "foods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    private String id;
    private String name;
    private List<String> ingredients; 
    private double price;
    private String imageUrl;
    private Size size;
    private Brand brand;
    private int stockQuantity;
    private double discount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private FoodCategory foodCategory;

    @DBRef
    private Supplier supplier;

    private NutritionalInfo nutritionalInfo;
}
