package com.webshop.webshopbackend.domain.repository;

import com.webshop.webshopbackend.domain.entity.VerificationToken;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
    boolean existsById(@NonNull String id);
    Optional<VerificationToken> findByToken(String token);
}
