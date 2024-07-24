package com.example.backend.services;

import com.example.backend.models.CartItem;
import com.example.backend.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    // Create or update a cart item
    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    // Get a cart item by its ID
    public Optional<CartItem> getCartItemById(String id) {
        return cartItemRepository.findById(id);
    }

    // Delete a cart item by its ID
    public void deleteCartItem(String id) {
        cartItemRepository.deleteById(id);
    }

    // Update the quantity of a cart item
    public CartItem updateCartItemQuantity(String id, int newQuantity) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(newQuantity);
            return cartItemRepository.save(cartItem);
        } else {
            throw new RuntimeException("CartItem not found");
        }
    }

    // Update the price of a cart item
    public CartItem updateCartItemPrice(String id, double newPrice) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setFood(cartItem.getFood()); // Update the food object with new price if needed
            return cartItemRepository.save(cartItem);
        } else {
            throw new RuntimeException("CartItem not found");
        }
    }

    // Calculate total price of all items in a cart
    public double calculateCartTotal(List<CartItem> cartItems) {
        return cartItems.stream()
                        .mapToDouble(CartItem::calculateTotalPrice)
                        .sum();
    }
}
