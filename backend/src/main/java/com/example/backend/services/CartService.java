package com.example.backend.services;

import com.example.backend.models.Cart;
import com.example.backend.models.CartItem;
import com.example.backend.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Create or update a cart
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get a cart by its ID
    public Optional<Cart> getCartById(String id) {
        return cartRepository.findById(id);
    }

    // Add an item to the cart
    public Cart addItemToCart(String cartId, CartItem cartItem) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<CartItem> items = cart.getItems();
            items.add(cartItem);
            cart.setItems(items);
            return cartRepository.save(cart);
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    // Remove an item from the cart
    public Cart removeItemFromCart(String cartId, String cartItemId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<CartItem> items = cart.getItems();
            items = items.stream().filter(item -> !item.getId().equals(cartItemId)).collect(Collectors.toList());
            cart.setItems(items);
            return cartRepository.save(cart);
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    // Calculate the total price of the cart
    public double calculateTotalPrice(String cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            return cart.getItems().stream()
                    .mapToDouble(item -> item.getDiscountedPrice() * item.getQuantity())
                    .sum();
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    // Clear all items from the cart
    public Cart clearCart(String cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setItems(List.of());
            return cartRepository.save(cart);
        } else {
            throw new RuntimeException("Cart not found");
        }
    }
}
