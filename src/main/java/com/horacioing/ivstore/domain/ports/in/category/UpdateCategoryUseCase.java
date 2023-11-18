package com.horacioing.ivstore.domain.ports.in.category;

import com.horacioing.ivstore.domain.models.category.Category;

import java.util.Optional;

public interface UpdateCategoryUseCase {
    Optional<Category> updateCategory(Category category);
}
