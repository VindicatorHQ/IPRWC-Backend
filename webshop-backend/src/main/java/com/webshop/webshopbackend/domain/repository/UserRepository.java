package com.webshop.webshopbackend.domain.repository;

import com.webshop.webshopbackend.domain.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(@NonNull String id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
