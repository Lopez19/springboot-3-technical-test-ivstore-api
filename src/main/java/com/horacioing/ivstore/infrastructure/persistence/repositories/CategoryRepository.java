package com.horacioing.ivstore.infrastructure.persistence.repositories;

import com.horacioing.ivstore.infrastructure.persistence.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
    Optional<CategoryEntity> findByName(String name);
}
