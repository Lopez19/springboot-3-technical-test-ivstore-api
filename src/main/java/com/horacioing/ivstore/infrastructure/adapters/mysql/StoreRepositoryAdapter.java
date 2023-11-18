package com.horacioing.ivstore.infrastructure.adapters.mysql;

import com.horacioing.ivstore.domain.models.store.Store;
import com.horacioing.ivstore.domain.ports.out.StoreRepositoryPort;
import com.horacioing.ivstore.infrastructure.persistence.entities.StoreEntity;
import com.horacioing.ivstore.infrastructure.persistence.mappers.AddressMapper;
import com.horacioing.ivstore.infrastructure.persistence.mappers.StoreMapper;
import com.horacioing.ivstore.infrastructure.persistence.repositories.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class StoreRepositoryAdapter implements StoreRepositoryPort {

    private final StoreRepository repository;
    private final StoreMapper mapper;
    private final AddressMapper addressMapper;

    @Override
    public Store save(Store store) {
        StoreEntity entity = this.repository.save(
                StoreEntity.builder()
                        .name(store.getName())
                        .address(this.addressMapper.toEntity(store.getAddress()))
                        .phone(store.getPhone())
                        .color(store.getColor())
                        .build()
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
    public Optional<Store> findById(Long id) {
        return this.repository.findById(id).map(this.mapper::toDomain);
    }

    @Override
    public Page<Store> findAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(this.mapper::toDomain);
    }

    @Override
    public Optional<Store> update(Long id, Store store) {
        return this.repository.findById(id).map(
                entity -> {
                    entity.setName(store.getName());
                    entity.setAddress(this.addressMapper.toEntity(store.getAddress()));
                    entity.setPhone(store.getPhone());
                    entity.setColor(store.getColor());
                    return this.mapper.toDomain(this.repository.save(entity));
                }
        );
    }

    @Override
    public Optional<Store> findByName(String name) {
        return this.repository.findByName(name).map(this.mapper::toDomain);
    }
}
