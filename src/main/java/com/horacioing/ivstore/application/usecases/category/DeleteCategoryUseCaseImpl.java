package com.horacioing.ivstore.application.usecases.category;

import com.horacioing.ivstore.domain.ports.in.category.DeleteCategoryUseCase;
import com.horacioing.ivstore.domain.ports.out.CategoryRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {
    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public boolean deleteCategory(Long id) {
        return categoryRepositoryPort.deleteById(id);
    }
}
