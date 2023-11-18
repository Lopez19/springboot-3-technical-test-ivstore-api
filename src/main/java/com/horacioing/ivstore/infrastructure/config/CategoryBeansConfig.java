package com.horacioing.ivstore.infrastructure.config;

import com.horacioing.ivstore.application.services.CategoryService;
import com.horacioing.ivstore.application.usecases.category.CreateCategoryUseCaseImpl;
import com.horacioing.ivstore.application.usecases.category.DeleteCategoryUseCaseImpl;
import com.horacioing.ivstore.application.usecases.category.RetrieveCategoryUseCaseImpl;
import com.horacioing.ivstore.application.usecases.category.UpdateCategoryUseCaseImpl;
import com.horacioing.ivstore.domain.ports.out.CategoryRepositoryPort;
import com.horacioing.ivstore.infrastructure.adapters.mysql.CategoryRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryBeansConfig {
    @Bean
    public CategoryService categoryService(CategoryRepositoryPort categoryRepositoryPort) {
        return new CategoryService(
                new CreateCategoryUseCaseImpl(categoryRepositoryPort),
                new DeleteCategoryUseCaseImpl(categoryRepositoryPort),
                new UpdateCategoryUseCaseImpl(categoryRepositoryPort),
                new RetrieveCategoryUseCaseImpl(categoryRepositoryPort)
        );
    }

    @Bean
    public CategoryRepositoryPort categoryRepositoryPort(CategoryRepositoryAdapter categoryRepositoryAdapter) {
        return categoryRepositoryAdapter;
    }
}
