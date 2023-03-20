package com.webshop.webshopbackend.domain.repository;

import com.webshop.webshopbackend.domain.entity.Building;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, String> {
    boolean existsById(@NonNull String id);
}
