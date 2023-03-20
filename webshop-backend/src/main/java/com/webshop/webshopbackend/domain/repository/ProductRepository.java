package com.webshop.webshopbackend.domain.repository;

import com.webshop.webshopbackend.domain.entity.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsById(@NonNull String id);
}
