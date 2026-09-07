package com.dpdagrofresh.backend;

import com.dpdagrofresh.backend.model.Product;
import com.dpdagrofresh.backend.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadProducts(ProductRepository productRepository) {

        return args -> {

            if (productRepository.count() > 0) {
                return;
            }

            // Exotic Vegetables
            productRepository.save(new Product(
                    "Broccoli",
                    "exotic-vegetables",
                    "Fresh premium quality broccoli suitable for wholesale and commercial supply.",
                    "../frontend/images/products/exotic/broccoli.jpg"
            ));

            productRepository.save(new Product(
                    "Asparagus",
                    "exotic-vegetables",
                    "Fresh premium quality asparagus suitable for wholesale supply.",
                    "../frontend/images/products/exotic/asparagus.jpg"
            ));

            productRepository.save(new Product(
                    "Bell Pepper",
                    "exotic-vegetables",
                    "Fresh colorful premium bell peppers for commercial and wholesale supply.",
                    "../frontend/images/products/exotic/bell-pepper.jpg"
            ));

            productRepository.save(new Product(
                    "Zucchini",
                    "exotic-vegetables",
                    "Fresh premium quality zucchini suitable for wholesale supply.",
                    "../frontend/images/products/exotic/zucchini.jpg"
            ));

            productRepository.save(new Product(
                    "Broccoli Rabe",
                    "exotic-vegetables",
                    "Fresh specialty green vegetable suitable for commercial supply.",
                    "../frontend/images/products/exotic/broccoli-rabe.jpg"
            ));

            // Indian Vegetables
            productRepository.save(new Product(
                    "Brinjal",
                    "vegetables",
                    "Fresh quality brinjal suitable for wholesale supply.",
                    "../frontend/images/products/vegetables/brinjal.jpg"
            ));

            productRepository.save(new Product(
                    "Cauliflower",
                    "vegetables",
                    "Fresh quality cauliflower suitable for wholesale supply.",
                    "../frontend/images/products/vegetables/cauliflower.jpg"
            ));

            productRepository.save(new Product(
                    "Cabbage",
                    "vegetables",
                    "Fresh quality cabbage suitable for wholesale supply.",
                    "../frontend/images/products/vegetables/cabbage.jpg"
            ));

            productRepository.save(new Product(
                    "Carrot",
                    "vegetables",
                    "Fresh quality carrots suitable for wholesale supply.",
                    "../frontend/images/products/vegetables/carrot.jpg"
            ));

            // Pulses
            productRepository.save(new Product(
                    "Chickpeas",
                    "pulses",
                    "Quality chickpeas suitable for wholesale and commercial supply.",
                    "../frontend/images/products/pulses/chickpeas.jpg"
            ));

            productRepository.save(new Product(
                    "Green Gram",
                    "pulses",
                    "Premium quality green gram suitable for wholesale supply.",
                    "../frontend/images/products/pulses/green-gram.jpg"
            ));

            productRepository.save(new Product(
                    "Kidney Beans",
                    "pulses",
                    "Quality kidney beans suitable for wholesale supply.",
                    "../frontend/images/products/pulses/kidney-beans.jpg"
            ));

            // Domestic Fruits
            productRepository.save(new Product(
                    "Mango",
                    "domestic-fruits",
                    "Fresh seasonal mangoes suitable for wholesale and commercial supply.",
                    "../frontend/images/products/fruits/domestic/mango.jpg"
            ));

            productRepository.save(new Product(
                    "Apple",
                    "domestic-fruits",
                    "Fresh quality apples suitable for wholesale supply.",
                    "../frontend/images/products/fruits/domestic/apple.jpg"
            ));

            productRepository.save(new Product(
                    "Banana",
                    "domestic-fruits",
                    "Fresh quality bananas suitable for wholesale supply.",
                    "../frontend/images/products/fruits/domestic/banana.jpg"
            ));

            // Exotic Fruits
            productRepository.save(new Product(
                    "Kiwi",
                    "exotic-fruits",
                    "Premium quality exotic kiwi suitable for wholesale supply.",
                    "../frontend/images/products/fruits/exotic/kiwi.jpg"
            ));

            productRepository.save(new Product(
                    "Dragon Fruit",
                    "exotic-fruits",
                    "Fresh premium dragon fruit suitable for commercial supply.",
                    "../frontend/images/products/fruits/exotic/dragon-fruit.jpg"
            ));

            System.out.println("========================================");
            System.out.println("17 PRODUCTS INSERTED SUCCESSFULLY");
            System.out.println("========================================");
        };
    }
}