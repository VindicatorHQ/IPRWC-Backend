package com.webshop.webshopbackend.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BuildingDTO {

    private String id;
    @NotBlank(message="Zipcode cannot be empty.")
    private String zipcode;
    @NotBlank(message="City cannot be empty.")
    private String city;
    @NotBlank(message="Address cannot be empty.")
    private String address;
    @NotBlank(message="Building name cannot be empty.")
    private String name;
}
