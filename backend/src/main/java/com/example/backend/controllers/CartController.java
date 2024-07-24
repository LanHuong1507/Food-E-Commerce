package com.example.backend.controllers;

import com.example.backend.models.Cart;
import com.example.backend.models.CartItem;
import com.example.backend.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    // Create or update a cart
    @PostMapping
    public ResponseEntity<Cart> createOrUpdateCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.saveCart(cart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    // Get a cart by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable String id) {
        Optional<Cart> cart = cartService.getCartById(id);
        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add an item to the cart
    @PostMapping("/{cartId}/items")
    public ResponseEntity<Cart> addItemToCart(@PathVariable String cartId, @RequestBody CartItem cartItem) {
        try {
            Cart updatedCart = cartService.addItemToCart(cartId, cartItem);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Remove an item from the cart
    @DeleteMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable String cartId, @PathVariable String cartItemId) {
        try {
            Cart updatedCart = cartService.removeItemFromCart(cartId, cartItemId);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Calculate the total price of the cart
    @GetMapping("/{cartId}/totalPrice")
    public ResponseEntity<Double> calculateTotalPrice(@PathVariable String cartId) {
        try {
            double totalPrice = cartService.calculateTotalPrice(cartId);
            return new ResponseEntity<>(totalPrice, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Clear all items from the cart
    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Cart> clearCart(@PathVariable String cartId) {
        try {
            Cart updatedCart = cartService.clearCart(cartId);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
