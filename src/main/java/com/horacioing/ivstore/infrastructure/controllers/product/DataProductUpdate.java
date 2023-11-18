package com.horacioing.ivstore.infrastructure.controllers.product;

import com.horacioing.ivstore.domain.models.category.Category;
import com.horacioing.ivstore.domain.models.product.Size;

import java.math.BigDecimal;

public record DataProductUpdate(
        Long id,
        String name,
        String description,
        String color,
        Category category,
        Size size,
        BigDecimal price
) {
}
