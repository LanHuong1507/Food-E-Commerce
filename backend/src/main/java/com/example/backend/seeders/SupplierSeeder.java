/*package com.example.backend.seeders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.backend.models.Supplier;
import com.example.backend.repositories.SupplierRepository;
import com.github.javafaker.Faker;

@Component
public class SupplierSeeder implements CommandLineRunner {

    @Autowired
    private SupplierRepository supplierRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
        supplierRepository.deleteAll();

        // Create and save multiple suppliers
        List<Supplier> suppliers = createRandomSuppliers(20);
        supplierRepository.saveAll(suppliers);
    }

    private List<Supplier> createRandomSuppliers(int number) {
        return Stream.generate(this::createRandomSupplier)
                     .limit(number)
                     .collect(Collectors.toList());
    }

    private Supplier createRandomSupplier() {
        // Generate a random createdAt date within the past year
        LocalDateTime createdAt = LocalDateTime.now().minusDays(random.nextInt(365));

        // Generate an updatedAt date within one year after createdAt
        LocalDateTime updatedAt = createdAt.plusDays(random.nextInt(365));

        return new Supplier(
            null,
            faker.company().name(),
            faker.phoneNumber().phoneNumber(),
            faker.address().fullAddress(),
            faker.internet().emailAddress(),
            faker.lorem().sentence(),
            faker.internet().url(),
            faker.commerce().department(),
            faker.number().randomDouble(1, 1, 5),
            createdAt,
            updatedAt
        );
    }
}
*/