package com.horacioing.ivstore.application.services;

import com.horacioing.ivstore.domain.models.store.Store;
import com.horacioing.ivstore.domain.ports.in.store.CreateStoreUseCase;
import com.horacioing.ivstore.domain.ports.in.store.DeleteStoreUseCase;
import com.horacioing.ivstore.domain.ports.in.store.RetrieveStoreUseCase;
import com.horacioing.ivstore.domain.ports.in.store.UpdateStoreUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class StoreService implements CreateStoreUseCase, UpdateStoreUseCase, DeleteStoreUseCase, RetrieveStoreUseCase {

    private final CreateStoreUseCase createStoreUseCase;
    private final UpdateStoreUseCase updateStoreUseCase;
    private final DeleteStoreUseCase deleteStoreUseCase;
    private final RetrieveStoreUseCase retrieveStoreUseCase;

    @Override
    public Store createStore(Store store) {
        return this.createStoreUseCase.createStore(store);
    }

    @Override
    public boolean deleteStore(Long id) {
        return this.deleteStoreUseCase.deleteStore(id);
    }

    @Override
    public Optional<Store> getStore(Long id) {
        return this.retrieveStoreUseCase.getStore(id);
    }

    @Override
    public Optional<Store> getStoreByName(String name) {
        return this.retrieveStoreUseCase.getStoreByName(name);
    }

    @Override
    public Page<Store> getStores(Pageable pageable) {
        return this.retrieveStoreUseCase.getStores(pageable);
    }

    @Override
    public Optional<Store> updateStore(Store store) {
        return this.updateStoreUseCase.updateStore(store);
    }
}
