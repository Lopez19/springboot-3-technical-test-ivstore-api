package com.horacioing.ivstore.infrastructure.adapters.mysql;

import com.horacioing.ivstore.domain.models.product.Product;
import com.horacioing.ivstore.domain.ports.out.ProductRepositoryPort;
import com.horacioing.ivstore.infrastructure.persistence.entities.ProductEntity;
import com.horacioing.ivstore.infrastructure.persistence.mappers.CategoryMapper;
import com.horacioing.ivstore.infrastructure.persistence.mappers.ProductMapper;
import com.horacioing.ivstore.infrastructure.persistence.mappers.SizeMapper;
import com.horacioing.ivstore.infrastructure.persistence.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    public final ProductRepository repository;
    public final ProductMapper mapper;
    private final CategoryMapper categoryMapper;
    private final SizeMapper sizeMapper;

    @Override
    public Product save(Product product) {
        ProductEntity entity = this.repository.save(
                this.mapper.toEntity(product)
        );
        return this.mapper.toDomain(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!this.repository.existsById(id)) {
            return false;
        }
        this.repository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.repository.findById(id).map(this.mapper::toDomain);
    }

    @Override
    public Page<Product> findAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(this.mapper::toDomain);
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        return this.repository.findById(id).map(
                entity -> {
                    entity.setName(product.getName());
                    entity.setDescription(product.getDescription());
                    entity.setColor(product.getColor());
                    entity.setCategory(this.categoryMapper.toEntity(product.getCategory()));
                    entity.setSize(this.sizeMapper.toEntity(product.getSize()));
                    entity.setPrice(product.getPrice());
                    return this.mapper.toDomain(this.repository.save(entity));
                }
        );
    }

    @Override
    public Optional<Product> findByReference(String reference) {
        return this.repository.findByReference(reference).map(this.mapper::toDomain);
    }
}
