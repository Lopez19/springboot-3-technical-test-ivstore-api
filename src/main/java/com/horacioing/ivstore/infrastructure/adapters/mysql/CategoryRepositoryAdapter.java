package com.horacioing.ivstore.infrastructure.adapters.mysql;

import com.horacioing.ivstore.domain.models.category.Category;
import com.horacioing.ivstore.domain.ports.out.CategoryRepositoryPort;
import com.horacioing.ivstore.infrastructure.persistence.entities.CategoryEntity;
import com.horacioing.ivstore.infrastructure.persistence.mappers.CategoryMapper;
import com.horacioing.ivstore.infrastructure.persistence.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public Optional<Category> findByName(String name) {
        return this.repository.findByName(name).map(this.mapper::toDomain);
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = this.repository.save(
                CategoryEntity.builder()
                        .name(category.getName().toLowerCase())
                        .description(category.getDescription().toLowerCase())
                        .color(category.getColor().toLowerCase())
                        .build()
        );
        return this.mapper.toDomain(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!this.repository.existsById(id)) {
            return Boolean.FALSE;
        }
        this.repository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.repository.findById(id).map(this.mapper::toDomain);
    }

    @Override
    public Page<Category> findAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(this.mapper::toDomain);
    }

    @Override
    public Optional<Category> update(Long id, Category category) {
        return this.repository.findById(id).map(
                entity -> {
                    entity.setName(category.getName().toLowerCase());
                    entity.setDescription(category.getDescription().toLowerCase());
                    entity.setColor(category.getColor().toLowerCase());
                    return this.mapper.toDomain(this.repository.save(entity));
                }
        );
    }
}
