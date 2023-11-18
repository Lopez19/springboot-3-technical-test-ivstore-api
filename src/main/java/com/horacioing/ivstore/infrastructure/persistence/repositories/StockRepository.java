package com.horacioing.ivstore.infrastructure.persistence.repositories;

import com.horacioing.ivstore.infrastructure.persistence.entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByProductReference(String productReference);

    @Modifying
    @Query("""
                  UPDATE StockEntity s
                  SET s.quantity = s.quantity - :quantity
                  WHERE s.product.reference = :productReference
            """)
    Integer updateByProductReference(String productReference, Integer quantity);
}
