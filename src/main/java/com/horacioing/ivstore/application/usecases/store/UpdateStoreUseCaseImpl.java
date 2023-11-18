package com.horacioing.ivstore.application.usecases.store;

import com.horacioing.ivstore.domain.models.store.Store;
import com.horacioing.ivstore.domain.ports.in.store.UpdateStoreUseCase;
import com.horacioing.ivstore.domain.ports.out.StoreRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateStoreUseCaseImpl implements UpdateStoreUseCase {
    private final StoreRepositoryPort storeRepositoryPort;

    @Override
    public Optional<Store> updateStore(Store store) {
        return this.storeRepositoryPort.update(store.getId(), store);
    }
}
