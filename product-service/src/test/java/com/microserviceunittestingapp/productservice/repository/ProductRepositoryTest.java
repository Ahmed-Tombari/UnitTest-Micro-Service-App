package com.microserviceunittestingapp.productservice.repository;

import com.microserviceunittestingapp.productservice.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
        System.out.println("-----------------------------------------------");
        productRepository.save(Product.builder()
                .id(UUID.randomUUID().toString())
                .name("Computer")
                .price(3200)
                .quantity(11)
                .build());
        productRepository.save(Product.builder()
                .id(UUID.randomUUID().toString())
                .name("Printer")
                .price(1299)
                .quantity(10)
                .build());
        productRepository.save(Product.builder()
                .id(UUID.randomUUID().toString())
                .name("Smart Phone")
                .price(5400)
                .quantity(8)
                .build());
        System.out.println("-----------------------------------------------");
    }
    @Test
    void shouldFindProductsByNameContaining(){
        List<Product> expected = List.of(
                Product.builder()
                        .id(UUID.randomUUID().toString())
                        .name("Smart Phone")
                        .price(5400)
                        .quantity(8)
                        .build(),
                Product.builder()
                        .id(UUID.randomUUID().toString())
                        .name("Computer")
                        .price(3200)
                        .quantity(11).build()
        );
        List<Product> result = productRepository.findByNameContainingIgnoreCase("m");
        //assertEquals(result.size(),2);
        assertThat(result.size()).isEqualTo(2);
        assertThat(expected.size()).isEqualTo(2);

        List<Product> sortedActualProducts = result.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        List<Product> sortedExpectedProducts = expected.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        assertThat(sortedExpectedProducts).usingRecursiveComparison().ignoringFields("id").isEqualTo(sortedActualProducts);
    }

    @Test
    void shouldHandleInvalidSearchTerm() {
        String keyword = "xyz123";
        List<Product> result = productRepository.findByNameContainingIgnoreCase(keyword);
        assertTrue(result.isEmpty());
    }
}