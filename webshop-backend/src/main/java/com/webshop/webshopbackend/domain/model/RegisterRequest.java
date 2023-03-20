package com.webshop.webshopbackend.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "First name cannot be empty.")
    private String name;
    @NotBlank(message = "Last name cannot be empty.")
    private String lastName;
    @NotBlank(message = "Email cannot be empty.")
    @Email
    private String email;
    @NotBlank(message = "Password cannot be empty.")
    private String password;
}
