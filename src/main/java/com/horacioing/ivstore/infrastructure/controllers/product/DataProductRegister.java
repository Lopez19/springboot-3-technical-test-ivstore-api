package com.horacioing.ivstore.infrastructure.controllers.product;

import com.horacioing.ivstore.domain.models.category.Category;
import com.horacioing.ivstore.domain.models.product.Size;

import java.math.BigDecimal;

public record DataProductRegister(
        String name,
        String description,
        BigDecimal price,
        String color,
        Category category,
        Size size,
        String reference
) {
}
