package com.horacioing.ivstore.infrastructure.persistence.repositories;

import com.horacioing.ivstore.infrastructure.persistence.entities.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    Optional<StoreEntity> findByName(String name);
}
