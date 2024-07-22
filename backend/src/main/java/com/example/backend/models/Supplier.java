package com.example.backend.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
