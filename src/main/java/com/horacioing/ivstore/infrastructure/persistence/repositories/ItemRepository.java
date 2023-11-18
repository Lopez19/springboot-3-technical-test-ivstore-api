package com.horacioing.ivstore.infrastructure.persistence.repositories;

import com.horacioing.ivstore.infrastructure.persistence.entities.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query("""
            SELECT i
            FROM ItemEntity i
            WHERE i.sale.id = :id
            """
    )
    Page<ItemEntity> findAllBySaleId(Long id, Pageable pagination);
}
