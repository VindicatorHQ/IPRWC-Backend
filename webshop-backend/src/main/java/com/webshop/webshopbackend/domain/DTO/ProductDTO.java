package com.webshop.webshopbackend.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
    private String id;

    @NotBlank(message = "Product name cannot be empty.")
    private String name;

    @NotBlank(message = "Product imageName cannot be empty.")
    private String imageName;

    private String description;

    @NotNull(message = "Product stock cannot be empty.")
    private int stock;

    @NotNull(message = "Product price cannot be empty.")
    private double price;

    @NotBlank(message = "Category id cannot be empty.")
    private String categoryId;
}
