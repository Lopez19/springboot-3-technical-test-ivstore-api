package com.horacioing.ivstore.application.usecases.store;

import com.horacioing.ivstore.domain.ports.in.store.DeleteStoreUseCase;
import com.horacioing.ivstore.domain.ports.out.StoreRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteStoreUseCaseImpl implements DeleteStoreUseCase {
    private final StoreRepositoryPort storeRepositoryPort;

    @Override
    public boolean deleteStore(Long id) {
        return this.storeRepositoryPort.deleteById(id);
    }
}
