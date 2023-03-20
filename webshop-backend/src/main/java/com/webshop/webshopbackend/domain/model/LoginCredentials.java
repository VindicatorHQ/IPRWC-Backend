package com.webshop.webshopbackend.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginCredentials {

    @NotBlank(message = "Email cannot be empty.")
    private String email;
    @NotBlank(message = "Password")
    private String password;

}
