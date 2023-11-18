package com.horacioing.ivstore.infrastructure.persistence.entities;

import com.horacioing.ivstore.domain.models.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "ProductEntity")
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String color;

    @ManyToOne(targetEntity = CategoryEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Enumerated(EnumType.STRING)
    private SizeEn size;

    private String reference;

    private BigDecimal price;
}
