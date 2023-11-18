package com.horacioing.ivstore.domain.ports.in.category;

import com.horacioing.ivstore.domain.models.category.Category;

public interface CreateCategoryUseCase {
    Category createCategory(Category category);
}
