package com.microserviceunittestingapp.productservice;

import com.microserviceunittestingapp.productservice.entities.Product;
import com.microserviceunittestingapp.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    @Profile("!test")
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            log.info("================= Initialization ================");
            List<Product> products = List.of(
                    Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name("Computer")
                            .price(3200)
                            .quantity(11)
                            .build(),
                    Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name("Printer")
                            .price(1299)
                            .quantity(10)
                            .build(),
                    Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name("Smart Phone")
                            .price(5400)
                            .quantity(8)
                            .build()
            );
            productRepository.saveAll(products);
            log.info("Products saved successfully: {}", products);

            productRepository.findAll().forEach(p->{
                System.out.println("======================");
                System.out.println(p.getId());
                System.out.println(p.getName());
                System.out.println(p.getPrice());
                System.out.println(p.getQuantity());
                System.out.println("=======================");
            });
        };
    }
}
