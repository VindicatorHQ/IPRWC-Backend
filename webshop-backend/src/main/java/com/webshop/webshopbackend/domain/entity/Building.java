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
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column
    @NotBlank(message = "Zipcode cannot be empty.")
    private String zipcode;

    @Column
    @NotBlank(message = "City cannot be empty.")
    private String city;

    @Column
    @NotBlank(message = "Address cannot be empty.")
    private String address;

    @Column
    @NotBlank(message = "Building name cannot be empty.")
    private String name;
}
