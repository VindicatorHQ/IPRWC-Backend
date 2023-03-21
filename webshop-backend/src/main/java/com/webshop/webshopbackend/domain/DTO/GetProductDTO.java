package com.webshop.webshopbackend.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetProductDTO {
    private String id;

    @NotBlank(message = "Product name cannot be empty.")
    private String name;

    @NotBlank(message = "Product stock cannot be empty.")
    private int stock;

    @NotBlank(message = "Product price cannot be empty.")
    private double price;

    @NotBlank(message = "Category name cannot be empty.")
    private String categoryName;
}
