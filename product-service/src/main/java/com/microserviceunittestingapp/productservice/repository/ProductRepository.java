package com.microserviceunittestingapp.productservice.repository;

import com.microserviceunittestingapp.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findByNameContainingIgnoreCase(String keyword);
    Optional<Product> findById(String id);
    Optional<Product> findByName(String name);
}
