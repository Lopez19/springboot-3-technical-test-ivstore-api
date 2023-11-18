package com.horacioing.ivstore.domain.ports.in.store;

import com.horacioing.ivstore.domain.models.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RetrieveStoreUseCase {
    Optional<Store> getStore(Long id);
    Optional<Store> getStoreByName(String name);
    Page<Store> getStores(Pageable pageable);
}
