package com.example.backend.enums;

public enum CartStatus {
    ACTIVE,     // The cart is currently active and items can be added or removed
    COMPLETED,  // The cart has been checked out and cannot be modified
    CANCELLED ,  // The cart has been cancelled and is no longer valid
    PENDING,    // The cart is pending and waiting for payment
}