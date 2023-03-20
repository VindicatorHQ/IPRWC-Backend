package com.webshop.webshopbackend.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {

    private String id;
    @NotBlank(message="Category name cannot be empty.")
    private String name;
}
