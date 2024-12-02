package com.microserviceunittestingapp.productservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class Product {
    @Id
    private String id;
    @NotEmpty @Size(min = 3)
    @Column(unique = true)
    private String name;
    private double price;
    private int quantity;
}
