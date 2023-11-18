package com.horacioing.ivstore.domain.ports.in.store;

import com.horacioing.ivstore.domain.models.store.Store;

public interface CreateStoreUseCase {
    Store createStore(Store store);
}
