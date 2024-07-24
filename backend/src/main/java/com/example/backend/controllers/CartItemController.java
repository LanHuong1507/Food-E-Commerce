package com.example.backend.controllers;

import com.example.backend.models.CartItem;
import com.example.backend.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartItems")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    // Create or update a cart item
    @PostMapping
    public ResponseEntity<CartItem> createOrUpdateCartItem(@RequestBody CartItem cartItem) {
        CartItem savedCartItem = cartItemService.saveCartItem(cartItem);
        return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);
    }

    // Get a cart item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable String id) {
        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
        return cartItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a cart item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable String id) {
        try {
            cartItemService.deleteCartItem(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Update the quantity of a cart item
    @PutMapping("/{id}/quantity")
    public ResponseEntity<CartItem> updateCartItemQuantity(@PathVariable String id, @RequestParam int newQuantity) {
        try {
            CartItem updatedCartItem = cartItemService.updateCartItemQuantity(id, newQuantity);
            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Update the price of a cart item
    @PutMapping("/{id}/price")
    public ResponseEntity<CartItem> updateCartItemPrice(@PathVariable String id, @RequestParam double newPrice) {
        try {
            CartItem updatedCartItem = cartItemService.updateCartItemPrice(id, newPrice);
            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
