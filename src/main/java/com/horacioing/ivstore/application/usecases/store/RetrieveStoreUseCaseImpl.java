package com.horacioing.ivstore.application.usecases.store;

import com.horacioing.ivstore.domain.models.store.Store;
import com.horacioing.ivstore.domain.ports.in.store.RetrieveStoreUseCase;
import com.horacioing.ivstore.domain.ports.out.StoreRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class RetrieveStoreUseCaseImpl implements RetrieveStoreUseCase {

    private final StoreRepositoryPort storeRepositoryPort;

    @Override
    public Optional<Store> getStore(Long id) {
        return this.storeRepositoryPort.findById(id);
    }

    @Override
    public Optional<Store> getStoreByName(String name) {
        return this.storeRepositoryPort.findByName(name);
    }

    @Override
    public Page<Store> getStores(Pageable pageable) {
        return this.storeRepositoryPort.findAll(pageable);
    }
}
