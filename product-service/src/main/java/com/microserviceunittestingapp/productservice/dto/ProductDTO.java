package com.microserviceunittestingapp.productservice.dto;

import jakarta.persistence.*;
import lombok.*;
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class ProductDTO {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;
}

