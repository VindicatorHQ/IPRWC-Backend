package com.webshop.webshopbackend.common.util;

import com.google.common.hash.Hashing;
import com.webshop.webshopbackend.domain.DAO.CategoryDAO;
import com.webshop.webshopbackend.domain.DAO.ProductDAO;
import com.webshop.webshopbackend.domain.DAO.UserDAO;
import com.webshop.webshopbackend.domain.entity.Category;
import com.webshop.webshopbackend.domain.entity.Product;
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
    private final CategoryDAO categoryDAO;
    private final ProductDAO productDAO;
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

        Category category1 =
                categoryDAO.saveToDatabase(Category.builder()
                        .name("Lost Fart")
                        .build()
                );

        Product productMeme1 =
                productDAO.saveToDatabase(Product.builder()
                        .name("Destroyer")
                        .description("Big bonky boi do be bonking you mama")
                        .imageName("https://media.giphy.com/media/fIMushESr3iulAuSgZ/giphy.gif")
                        .stock(3)
                        .price(20.00)
                        .category(category1)
                        .build()
                );

        Product productMeme2 =
                productDAO.saveToDatabase(Product.builder()
                        .name("Summoner")
                        .description("Summoning on deez nuts ha got em")
                        .imageName("https://media.giphy.com/media/13CSvyHEOwoUM/giphy.gif")
                        .stock(69)
                        .price(5.00)
                        .category(category1)
                        .build()
                );

        Product productMeme3 =
                productDAO.saveToDatabase(Product.builder()
                        .name("Scouter")
                        .description("Robot transform be like BZZZ BZZZ KABOOM LASAAAAH FIRING")
                        .imageName("https://media.giphy.com/media/sJWNLTclcvVmw/giphy.gif")
                        .stock(420)
                        .price(10.11)
                        .category(category1)
                        .build()
                );

        Product productMeme4 =
                productDAO.saveToDatabase(Product.builder()
                        .name("Reaper")
                        .description("Deadge life be like")
                        .imageName("https://media.giphy.com/media/Kxy2YUDnDrvdxVsVb8/giphy.gif")
                        .stock(4)
                        .price(1.69)
                        .category(category1)
                        .build()
                );
    }
}
