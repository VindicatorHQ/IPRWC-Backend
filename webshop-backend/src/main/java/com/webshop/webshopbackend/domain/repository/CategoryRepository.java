package com.webshop.webshopbackend.domain.repository;

import com.webshop.webshopbackend.domain.entity.Category;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsById(@NonNull String id);
}
