package com.horacioing.ivstore.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ItemEntity")
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;

    @ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(targetEntity = SaleEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private SaleEntity sale;
}
