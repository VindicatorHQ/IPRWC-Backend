package com.webshop.webshopbackend.common.util;

import com.google.common.hash.Hashing;
import com.webshop.webshopbackend.domain.DAO.BuildingDAO;
import com.webshop.webshopbackend.domain.DAO.UserDAO;
import com.webshop.webshopbackend.domain.entity.Building;
import com.webshop.webshopbackend.domain.entity.User;
import com.webshop.webshopbackend.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final UserDAO userDAO;
    private final BuildingDAO buildingDAO;
    private final BCryptPasswordEncoder passwordEncoder;
    @Value("${admin.name}")
    private String name;
    @Value("${admin.lastName}")
    private String lastName;
    @Value("${admin.email}")
    private String email;
    @Value("${admin.password}")
    private String password;

    @Override
    public void run(String... args) {
        User admin = User.builder()
                .name(name)
                .lastName(lastName)
                .email(email)
                .password(
                        passwordEncoder.encode(
                                Hashing.sha256()
                                        .hashString(password, StandardCharsets.UTF_8)
                                        .toString())
                )
                .enabled(true)
                .role(Role.ADMIN)
                .build();
        userDAO.saveToDatabase(admin);

        User johnDoe = User.builder()
                .name("John")
                .lastName("Doe")
                .email("johndoe@gmail.com")
                .password(
                        passwordEncoder.encode(
                                Hashing.sha256()
                                        .hashString("password123", StandardCharsets.UTF_8)
                                        .toString())
                )
                .enabled(true)
                .role(Role.USER)
                .build();
        userDAO.saveToDatabase(johnDoe);

        Building building =
                buildingDAO.saveToDatabase(Building.builder()
                        .name("CGI Rotterdam")
                        .address("George Hintzenweg 83")
                        .zipcode("3068AX")
                        .city("Rotterdam")
                        .build()
                );
    }
}
