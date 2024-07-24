/*package com.example.backend.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.backend.models.User;
import com.example.backend.enums.Role; // Ensure this is the correct import
import com.example.backend.repositories.UserRepository;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        List<User> users = Stream.generate(this::createRandomUser)
                                 .limit(30)
                                 .collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    private User createRandomUser() {
        User user = new User();
        user.setUsername(faker.name().username());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password()); 
        user.setRole(getRandomRole()); 
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        user.setAddress(faker.address().fullAddress());
        return user;
    }

    private Role getRandomRole() {
        Role[] roles = Role.values();
        return roles[random.nextInt(roles.length)];
    }
}
*/