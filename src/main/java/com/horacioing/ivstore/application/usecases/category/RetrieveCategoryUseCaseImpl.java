package com.horacioing.ivstore.application.usecases.category;

import com.horacioing.ivstore.domain.models.category.Category;
import com.horacioing.ivstore.domain.ports.in.category.RetrieveCategoryUseCase;
import com.horacioing.ivstore.domain.ports.out.CategoryRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class RetrieveCategoryUseCaseImpl implements RetrieveCategoryUseCase {
    private final CategoryRepositoryPort categoryRepositoryPort;

    @Override
    public Optional<Category> getCategory(Long id) {
        return this.categoryRepositoryPort.findById(id);
    }

    @Override
    public Page<Category> getCategories(Pageable pageable) {
        return this.categoryRepositoryPort.findAll(pageable);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return this.categoryRepositoryPort.findByName(name);
    }
}
