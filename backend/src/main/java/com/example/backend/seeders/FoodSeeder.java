/*package com.example.backend.seeders;

import com.example.backend.enums.Brand;
import com.example.backend.enums.FoodCategory;
import com.example.backend.enums.Size;
import com.example.backend.models.Food;
import com.example.backend.models.NutritionalInfo;
import com.example.backend.models.Supplier;
import com.example.backend.repositories.CartRepository;
import com.example.backend.repositories.CartItemRepository;
import com.example.backend.repositories.FoodRepository;
import com.example.backend.repositories.SupplierRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FoodSeeder implements CommandLineRunner {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        foodRepository.deleteAll();
        supplierRepository.deleteAll();
        cartRepository.deleteAll();
        cartItemRepository.deleteAll();

        List<Supplier> suppliers = createRandomSuppliers(20);
        supplierRepository.saveAll(suppliers);

        List<Food> foods = createRandomFoods(suppliers);
        foodRepository.saveAll(foods);
    }

    private List<Food> createRandomFoods(List<Supplier> suppliers) {
        return Stream.generate(() -> {
            Food food = new Food();
            food.setName(faker.food().dish());
            food.setPrice(faker.number().randomDouble(2, 1, 100));
            food.setIngredients(generateRandomIngredients());
            food.setImageUrl(getRandomImageUrl());
            food.setSize(getRandomSize());
            food.setBrand(getRandomBrand());
            food.setStockQuantity(faker.number().numberBetween(1, 100));
            food.setDiscount(faker.number().randomDouble(2, 0, 20));
            food.setCreatedAt(LocalDateTime.now().minusDays(random.nextInt(365)));  
            food.setUpdatedAt(LocalDateTime.now().minusDays(random.nextInt(365)));  
            food.setFoodCategory(getRandomCategory());
            food.setSupplier(getRandomSupplier(suppliers));
            food.setNutritionalInfo(createRandomNutritionalInfo());

            // Ensure updatedAt is after createdAt
            if (food.getUpdatedAt().isBefore(food.getCreatedAt())) {
                food.setUpdatedAt(food.getCreatedAt().plusDays(random.nextInt(365)));
            }

            return food;
        }).limit(100).collect(Collectors.toList());
    }

    private List<String> generateRandomIngredients() {
        int numberOfIngredients = random.nextInt(3, 5) + 1;
        return Stream.generate(() -> faker.food().ingredient())
                     .limit(numberOfIngredients)
                     .collect(Collectors.toList());
    }

    private Size getRandomSize() {
        Size[] sizes = Size.values();
        return sizes[random.nextInt(sizes.length)];
    }

    private Brand getRandomBrand() {
        Brand[] brands = Brand.values();
        return brands[random.nextInt(brands.length)];
    }

    private FoodCategory getRandomCategory() {
        FoodCategory[] categories = FoodCategory.values();
        return categories[random.nextInt(categories.length)];
    }

    private NutritionalInfo createRandomNutritionalInfo() {
        return new NutritionalInfo(
            faker.number().randomDouble(2, 100, 1000), 
            faker.number().randomDouble(2, 0, 100),
            faker.number().randomDouble(2, 0, 100), 
            faker.number().randomDouble(2, 0, 100)
        );
    }

    private List<Supplier> createRandomSuppliers(int number) {
        return Stream.generate(this::createRandomSupplier)
                     .limit(number)
                     .collect(Collectors.toList());
    }

    private Supplier createRandomSupplier() {
        LocalDateTime createdAt = LocalDateTime.now().minusDays(random.nextInt(365));
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

    private Supplier getRandomSupplier(List<Supplier> suppliers) {
        return suppliers.get(random.nextInt(suppliers.size()));
    }

    private String getRandomImageUrl() {
        List<String> imageUrls = List.of(
            "https://i.imgur.com/wgifj8i.jpg",
            "https://i.imgur.com/wh5sm9Z.jpeg",
            "https://i.imgur.com/s3krK0T.jpeg",
            "https://i.imgur.com/c5oVVzM.png",
            "https://i.imgur.com/yunxKND.jpeg",
            "https://i.imgur.com/xVI4wzz.jpeg",
            "https://i.imgur.com/zi7Znvb.jpeg"
        );
        return imageUrls.get(random.nextInt(imageUrls.size()));
    }
    
}
*/