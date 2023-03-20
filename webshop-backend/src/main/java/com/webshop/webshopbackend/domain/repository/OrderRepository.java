package com.webshop.webshopbackend.domain.repository;

import com.webshop.webshopbackend.domain.entity.Order;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    boolean existsById(@NonNull String id);
}
