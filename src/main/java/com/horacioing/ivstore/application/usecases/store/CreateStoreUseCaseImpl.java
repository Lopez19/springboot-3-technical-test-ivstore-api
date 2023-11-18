package com.horacioing.ivstore.application.usecases.store;

import com.horacioing.ivstore.domain.models.store.Store;
import com.horacioing.ivstore.domain.ports.in.store.CreateStoreUseCase;
import com.horacioing.ivstore.domain.ports.out.StoreRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CreateStoreUseCaseImpl implements CreateStoreUseCase {
    private final StoreRepositoryPort storeRepositoryPort;

    @Override
    public Store createStore(Store store) {
        Optional<Store> storeFound = this.storeRepositoryPort.findByName(store.getName());
        return storeFound.orElseGet(() -> this.storeRepositoryPort.save(store));
    }
}
