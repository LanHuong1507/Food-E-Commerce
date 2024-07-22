package com.example.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend.models.Supplier;

public interface SupplierRepository extends MongoRepository<Supplier, String> {
    
}
