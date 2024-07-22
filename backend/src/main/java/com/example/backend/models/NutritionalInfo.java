package com.example.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NutritionalInfo {
    private double calories;
    private double fat;
    private double protein;
    private double carbohydrates;
}
