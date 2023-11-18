package com.horacioing.ivstore.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "StockEntity")
@Table(name = "stocks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private Integer quantity;

    @ManyToOne(targetEntity = StoreEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity store;
}
