package com.horacioing.ivstore.infrastructure.adapters.mysql;

import com.horacioing.ivstore.domain.models.stock.Stock;
import com.horacioing.ivstore.domain.ports.out.StockRepositoryPort;
import com.horacioing.ivstore.infrastructure.persistence.entities.StockEntity;
import com.horacioing.ivstore.infrastructure.persistence.mappers.ProductMapper;
import com.horacioing.ivstore.infrastructure.persistence.mappers.StockMapper;
import com.horacioing.ivstore.infrastructure.persistence.mappers.StoreMapper;
import com.horacioing.ivstore.infrastructure.persistence.repositories.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class StockRepositoryAdapter implements StockRepositoryPort {

    private final StockRepository repository;
    private final StockMapper mapper;
    private final ProductMapper productMapper;
    private final StoreMapper storeMapper;

    @Override
    public Stock save(Stock stock) {
        StockEntity entity = this.repository.save(
                this.mapper.toEntity(stock)
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
    public Optional<Stock> findById(Long id) {
        return this.repository.findById(id).map(this.mapper::toDomain);
    }

    @Override
    public Page<Stock> findAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(this.mapper::toDomain);
    }

    @Override
    public Optional<Stock> update(Long id, Stock stock) {
        return this.repository.findById(id).map(
                entity -> {
                    entity.setProduct(this.productMapper.toEntity(stock.getProduct()));
                    entity.setStore(this.storeMapper.toEntity(stock.getStore()));
                    entity.setQuantity(stock.getQuantity());
                    return this.mapper.toDomain(this.repository.save(entity));
                }
        );
    }

    @Override
    public Optional<Stock> findByProductReference(String productReference) {
        return this.repository.findByProductReference(productReference).map(this.mapper::toDomain);
    }

    @Override
    public Integer reduceStockByProductReference(String productReference, Integer quantity) {
        return this.repository.updateByProductReference(productReference, quantity);
    }
}
