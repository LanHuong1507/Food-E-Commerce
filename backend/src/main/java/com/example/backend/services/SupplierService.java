package com.example.backend.services;

import com.example.backend.models.Supplier;
import com.example.backend.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    // Add a new supplier
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Get a supplier by ID
    public Optional<Supplier> getSupplierById(String id) {
        return supplierRepository.findById(id);
    }

    // Update an existing supplier
    public Supplier updateSupplier(String id, Supplier updatedSupplier) {
        if (supplierRepository.existsById(id)) {
            updatedSupplier.setId(id);
            return supplierRepository.save(updatedSupplier);
        } else {
            throw new RuntimeException("Supplier not found with id: " + id);
        }
    }

    // Delete a supplier by ID
    public void deleteSupplier(String id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
        } else {
            throw new RuntimeException("Supplier not found with id: " + id);
        }
    }
}
