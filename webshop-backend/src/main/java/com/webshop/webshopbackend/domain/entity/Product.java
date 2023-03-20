package com.webshop.webshopbackend.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column
    @NotBlank(message="Product name cannot be empty.")
    private String name;

    @Column
    @NotBlank(message="Product stock cannot be empty.")
    private int stock;

    @Column
    @NotBlank(message="Product price cannot be empty.")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
