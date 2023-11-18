package com.horacioing.ivstore.infrastructure.adapters.mysql;

import com.horacioing.ivstore.domain.models.sale.Sale;
import com.horacioing.ivstore.domain.ports.out.SaleRepositoryPort;
import com.horacioing.ivstore.infrastructure.persistence.entities.SaleEntity;
import com.horacioing.ivstore.infrastructure.persistence.mappers.SaleMapper;
import com.horacioing.ivstore.infrastructure.persistence.repositories.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SaleRepositoryAdapter implements SaleRepositoryPort {

    private final SaleRepository repository;
    private final SaleMapper saleMapper;

    @Override
    public Sale save(Sale sale) {
        SaleEntity entity = this.repository.save(this.saleMapper.toEntity(sale));
        return this.saleMapper.toDomain(entity);
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
    public Optional<Sale> findById(Long id) {
        return this.repository.findById(id).map(this.saleMapper::toDomain);
    }

    @Override
    public Page<Sale> findAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(this.saleMapper::toDomain);
    }

    @Override
    public Optional<Sale> update(Long id, Sale sale) {
        return Optional.empty();
    }
}
