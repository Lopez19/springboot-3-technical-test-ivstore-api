package com.horacioing.ivstore.domain.ports.in.category;

import com.horacioing.ivstore.domain.models.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RetrieveCategoryUseCase{
    Optional<Category> getCategory(Long id);
    Page<Category> getCategories(Pageable pageable);
    Optional<Category> getCategoryByName(String name);
}
