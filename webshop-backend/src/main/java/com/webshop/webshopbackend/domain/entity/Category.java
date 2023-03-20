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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column
    @NotBlank(message="Category name cannot be empty.")
    private String name;
}
