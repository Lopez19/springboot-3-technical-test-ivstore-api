package com.horacioing.ivstore.infrastructure.persistence.repositories;

import com.horacioing.ivstore.infrastructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
    Optional<ProductEntity> findByReference(String reference);
}
