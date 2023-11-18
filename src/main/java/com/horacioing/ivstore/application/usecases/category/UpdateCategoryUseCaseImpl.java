package com.horacioing.ivstore.application.usecases.category;

import com.horacioing.ivstore.domain.models.category.Category;
import com.horacioing.ivstore.domain.ports.in.category.UpdateCategoryUseCase;
import com.horacioing.ivstore.domain.ports.out.CategoryRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Optional<Category> updateCategory(Category category) {
        return this.categoryRepositoryPort.update(category.getId(), category);
    }
}
