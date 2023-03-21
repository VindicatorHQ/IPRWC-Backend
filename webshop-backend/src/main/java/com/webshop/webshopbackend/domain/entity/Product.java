package com.webshop.webshopbackend.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

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
    @NotBlank(message = "Product name cannot be empty.")
    private String name;

    @Column
    private String description;

    @Column
    @NotBlank(message = "Product imageName cannot be empty.")
    private String imageName;

    @Column(columnDefinition = "integer default 0")
    private int stock;

    @Column(columnDefinition = "double default 1.00")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private Set<Order> orders = new LinkedHashSet<>();
}
