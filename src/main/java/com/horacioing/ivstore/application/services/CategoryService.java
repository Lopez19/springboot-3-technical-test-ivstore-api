package com.horacioing.ivstore.application.services;

import com.horacioing.ivstore.domain.models.category.Category;
import com.horacioing.ivstore.domain.ports.in.category.CreateCategoryUseCase;
import com.horacioing.ivstore.domain.ports.in.category.DeleteCategoryUseCase;
import com.horacioing.ivstore.domain.ports.in.category.RetrieveCategoryUseCase;
import com.horacioing.ivstore.domain.ports.in.category.UpdateCategoryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class CategoryService implements CreateCategoryUseCase, DeleteCategoryUseCase, UpdateCategoryUseCase, RetrieveCategoryUseCase {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final RetrieveCategoryUseCase retrieveCategoryUseCase;

    @Override
    public Category createCategory(Category category) {
        return this.createCategoryUseCase.createCategory(category);
    }

    @Override
    public boolean deleteCategory(Long id) {
        return this.deleteCategoryUseCase.deleteCategory(id);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return this.retrieveCategoryUseCase.getCategory(id);
    }

    @Override
    public Page<Category> getCategories(Pageable pageable) {
        return this.retrieveCategoryUseCase.getCategories(pageable);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return this.retrieveCategoryUseCase.getCategoryByName(name);
    }

    @Override
    public Optional<Category> updateCategory(Category category) {
        return this.updateCategoryUseCase.updateCategory(category);
    }

}
