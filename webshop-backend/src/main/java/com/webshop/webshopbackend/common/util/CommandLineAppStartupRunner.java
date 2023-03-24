package com.webshop.webshopbackend.common.util;

import com.google.common.hash.Hashing;
import com.webshop.webshopbackend.domain.DAO.CategoryDAO;
import com.webshop.webshopbackend.domain.DAO.OrderDAO;
import com.webshop.webshopbackend.domain.DAO.ProductDAO;
import com.webshop.webshopbackend.domain.DAO.UserDAO;
import com.webshop.webshopbackend.domain.entity.Category;
import com.webshop.webshopbackend.domain.entity.Order;
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
    private final OrderDAO productOrderDAO;
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

        Category category2 =
                categoryDAO.saveToDatabase(Category.builder()
                        .name("Oh no the Fard")
                        .build()
                );

        Product productMeme1 =
                productDAO.saveToDatabase(Product.builder()
                        .name("Destroyer")
                        .description("Big bonky boi do be bonking you mama")
                        .imageName("https://tenor.com/view/destroyer-destroy-angry-angry-panda-panda-gif-16909733.gif")
                        .stock(3)
                        .price(20.00)
                        .category(category1)
                        .build()
                );

        Product productMeme2 =
                productDAO.saveToDatabase(Product.builder()
                        .name("Summoner")
                        .description("Summoning on deez nuts ha got em")
                        .imageName("https://tenor.com/view/doctor-doom-summoned-victor-von-doom-power-gif-15859330.gif")
                        .stock(69)
                        .price(5.00)
                        .category(category1)
                        .build()
                );

        Product productMeme3 =
                productDAO.saveToDatabase(Product.builder()
                        .name("Scouter")
                        .description("Robot transform be like BZZZ BZZZ KABOOM LASAAAAH FIRING")
                        .imageName("https://tenor.com/view/dbz-vegeta-crush-scouter-over9000-gif-4896810.gif")
                        .stock(420)
                        .price(10.11)
                        .category(category1)
                        .build()
                );

        Product productMeme4 =
                productDAO.saveToDatabase(Product.builder()
                        .name("Reaper")
                        .description("Deadge life be like")
                        .imageName("https://tenor.com/view/meh-monday-morning-tired-coffee-break-gif-14544679.gif")
                        .stock(4)
                        .price(1.69)
                        .category(category1)
                        .build()
                );

        Product productMeme5 =
                productDAO.saveToDatabase(Product.builder()
                        .name("Fard Gru")
                        .description("Deadge life be like")
                        .imageName("https://tenor.com/view/dr-nefario-despicable-me5-despicable-me-despicable-me1-despicable-me2-gif-22205201.gif")
                        .stock(4)
                        .price(1.69)
                        .category(category2)
                        .build()
                );

        Product productMeme6 =
                productDAO.saveToDatabase(Product.builder()
                        .name("Fard Robux")
                        .description("Not those robux scammers again")
                        .imageName("https://tenor.com/view/roblox-table-gif-22987765.gif")
                        .stock(2)
                        .price(3.69)
                        .category(category2)
                        .build()
                );

        Order productOrder1 =
                productOrderDAO.saveToDatabase(Order.builder()
                        .date(new java.sql.Date(2023-1900, 4, 30))
                        .product(productMeme6)
                        .user(johnDoe)
                        .build()
                );

        Order productOrder2 =
                productOrderDAO.saveToDatabase(Order.builder()
                        .date(new java.sql.Date(2022-1900, 3, 30))
                        .product(productMeme3)
                        .user(johnDoe)
                        .build()
                );

        Order productOrder3 =
                productOrderDAO.saveToDatabase(Order.builder()
                        .date(new java.sql.Date(2024-1900, 6, 30))
                        .product(productMeme4)
                        .user(johnDoe)
                        .build()
                );

        Order productOrder4 =
                productOrderDAO.saveToDatabase(Order.builder()
                        .date(new java.sql.Date(2023-1900, 4, 30))
                        .product(productMeme6)
                        .user(admin)
                        .build()
                );

        Order productOrder5 =
                productOrderDAO.saveToDatabase(Order.builder()
                        .date(new java.sql.Date(2022-1900, 3, 30))
                        .product(productMeme3)
                        .user(admin)
                        .build()
                );

        Order productOrder6 =
                productOrderDAO.saveToDatabase(Order.builder()
                        .date(new java.sql.Date(2024-1900, 6, 30))
                        .product(productMeme4)
                        .user(admin)
                        .build()
                );
    }
}
