package com.webshop.webshopbackend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "product_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(nullable = false)
    private Date date;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private Set<Product> products = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
