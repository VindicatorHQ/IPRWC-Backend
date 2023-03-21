package com.webshop.webshopbackend.domain.repository;

import com.webshop.webshopbackend.domain.entity.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsById(@NonNull String id);

    List<Product> findByCategoryId(String categoryId);
}
