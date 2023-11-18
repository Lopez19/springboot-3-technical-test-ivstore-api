package com.horacioing.ivstore.infrastructure.controllers.category;

public record DataCategoryUpdate(
        Long id,
        String name,
        String description,
        String color
) {
}
