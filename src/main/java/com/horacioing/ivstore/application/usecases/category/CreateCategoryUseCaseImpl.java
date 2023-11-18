package com.horacioing.ivstore.application.usecases.category;

import com.horacioing.ivstore.domain.models.category.Category;
import com.horacioing.ivstore.domain.ports.in.category.CreateCategoryUseCase;
import com.horacioing.ivstore.domain.ports.out.CategoryRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Category createCategory(Category category) {
        Optional<Category> categoryFound = this.categoryRepositoryPort.findByName(category.getName());
        return categoryFound.orElseGet(() -> this.categoryRepositoryPort.save(category));
    }
}
