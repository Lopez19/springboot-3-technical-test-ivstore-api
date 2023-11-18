package com.horacioing.ivstore.infrastructure.adapters.mysql;

import com.horacioing.ivstore.domain.models.item.Item;
import com.horacioing.ivstore.domain.ports.out.ItemRepositoryPort;
import com.horacioing.ivstore.infrastructure.persistence.entities.ItemEntity;
import com.horacioing.ivstore.infrastructure.persistence.mappers.ItemMapper;
import com.horacioing.ivstore.infrastructure.persistence.mappers.ProductMapper;
import com.horacioing.ivstore.infrastructure.persistence.mappers.SaleMapper;
import com.horacioing.ivstore.infrastructure.persistence.repositories.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ItemRepositoryAdapter implements ItemRepositoryPort {

    private final ItemRepository repository;
    private final ItemMapper itemMapper;
    private final ProductMapper productMapper;
    private final SaleMapper saleMapper;

    @Override
    public Item save(Item item) {
        ItemEntity entity = this.repository.save(this.itemMapper.toEntity(item));
        return this.itemMapper.toDomain(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!this.repository.existsById(id)){
            return Boolean.FALSE;
        }
        this.repository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Optional<Item> findById(Long id) {
        return this.repository.findById(id).map(this.itemMapper::toDomain);
    }

    @Override
    public Page<Item> findAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(this.itemMapper::toDomain);
    }

    @Override
    public Optional<Item> update(Long id, Item item) {
        return Optional.empty();
    }

    @Override
    public Page<Item> findAllBySaleId(Long saleId, Pageable pagination) {
        return this.repository.findAllBySaleId(saleId, pagination).map(this.itemMapper::toDomain);
    }
}
