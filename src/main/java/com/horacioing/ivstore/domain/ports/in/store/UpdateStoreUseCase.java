package com.horacioing.ivstore.domain.ports.in.store;

import com.horacioing.ivstore.domain.models.store.Store;

import java.util.Optional;

public interface UpdateStoreUseCase {
    Optional<Store> updateStore(Store store);
}
