package com.webshop.webshopbackend.controller;

import com.webshop.webshopbackend.domain.model.JwtResponse;
import com.webshop.webshopbackend.domain.model.LoginCredentials;
import com.webshop.webshopbackend.domain.model.RegisterRequest;
import com.webshop.webshopbackend.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("${settings.redirectUrl}")
    private String redirectUrl;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerHandler(@RequestBody @Valid RegisterRequest registerRequest, HttpServletRequest request) {
        this.authService.register(registerRequest, request);
    }

    @GetMapping("/register/confirm")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> registrationConfirmationHandler(@RequestParam("token") String token) {
        authService.confirmRegistration(token);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse loginHandler(@RequestBody @Valid LoginCredentials request) {
        return authService.authenticate(request);
    }


}
