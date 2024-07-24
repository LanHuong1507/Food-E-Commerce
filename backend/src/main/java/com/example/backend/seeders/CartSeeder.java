package com.example.backend.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.backend.enums.CartStatus;
import com.example.backend.enums.PaymentMethod;
import com.example.backend.models.Cart;
import com.example.backend.models.CartItem;
import com.example.backend.models.Food;
import com.example.backend.models.User;
import com.example.backend.repositories.CartRepository;
import com.example.backend.repositories.CartItemRepository;
import com.example.backend.repositories.FoodRepository;
import com.example.backend.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CartSeeder implements CommandLineRunner {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting cart seeding...");

        cartRepository.deleteAll();
        cartItemRepository.deleteAll();

        List<Food> foods = foodRepository.findAll();
        List<User> users = userRepository.findAll();

        if (foods.isEmpty()) {
            System.out.println("No foods found. Cart seeding cannot proceed.");
            return;
        }

        if (users.isEmpty()) {
            System.out.println("No users found. Cart seeding cannot proceed.");
            return;
        }

        for (int i = 0; i < 10; i++) {
            Cart cart = new Cart();
            cart.setCreatedAt(LocalDateTime.now());
            cart.setUpdatedAt(LocalDateTime.now());
            cart.setTotalCost(0.0);
            cart.setUserId(users.get(random.nextInt(users.size())).getId());
            cart.setStatus(getRandomCartStatus());
            cart.setDeliveryAddress("Address " + (i + 1));
            cart.setPaymentMethod(getRandomPaymentMethod());
            cart.setNotes("Notes for cart " + (i + 1));

            // Save the cart first to get its ID
            cart = cartRepository.save(cart);
            System.out.println("Saved cart: " + cart.getId());

            List<CartItem> cartItems = createRandomCartItems(foods, cart);
            System.out.println("CartItems to save: " + cartItems);
            cartItemRepository.saveAll(cartItems);

            // After saving cart items, link them to the cart
            cart.setItems(cartItems);

            // Update the total cost after linking items
            cart.setTotalCost(calculateTotalCost(cartItems));
            cartRepository.save(cart); // Update the cart with items and total cost

            // Debugging info
            System.out.println("Cart ID: " + cart.getId() + " with items: " + cartItems.size());
        }
    }

    private List<CartItem> createRandomCartItems(List<Food> foods, Cart cart) {
        List<CartItem> cartItems = Stream.generate(() -> {
            Food food = foods.get(random.nextInt(foods.size()));
            CartItem item = new CartItem(
                null,
                food,
                random.nextInt(1, 5) + 1,
                cart
            );
            System.out.println("Creating CartItem: " + item);
            return item;
        }).limit(random.nextInt(1, 6))
          .collect(Collectors.toList());

        System.out.println("CartItems before saving: " + cartItems);
        return cartItems;
    }

    private double calculateTotalCost(List<CartItem> cartItems) {
        return cartItems.stream()
                        .mapToDouble(item -> item.getQuantity() * item.getFood().getPrice())
                        .sum();
    }

    private CartStatus getRandomCartStatus() {
        CartStatus[] statuses = CartStatus.values();
        return statuses[random.nextInt(statuses.length)];
    }

    private PaymentMethod getRandomPaymentMethod() {
        PaymentMethod[] methods = PaymentMethod.values();
        return methods[random.nextInt(methods.length)];
    }
}
