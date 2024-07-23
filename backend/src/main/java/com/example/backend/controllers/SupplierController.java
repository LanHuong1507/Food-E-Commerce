package com.example.backend.controllers;

import com.example.backend.models.Supplier;
import com.example.backend.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Create a new supplier
    @PostMapping
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
        Supplier createdSupplier = supplierService.addSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    // Get all suppliers
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    // Get a supplier by ID
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable String id) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);
        return supplier.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update a supplier
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable String id, @RequestBody Supplier supplier) {
        try {
            Supplier updatedSupplier = supplierService.updateSupplier(id, supplier);
            return ResponseEntity.ok(updatedSupplier);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a supplier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable String id) {
        try {
            supplierService.deleteSupplier(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
