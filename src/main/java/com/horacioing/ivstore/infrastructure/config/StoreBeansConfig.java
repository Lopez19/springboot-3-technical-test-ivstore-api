package com.horacioing.ivstore.infrastructure.config;

import com.horacioing.ivstore.application.services.StoreService;
import com.horacioing.ivstore.application.usecases.store.CreateStoreUseCaseImpl;
import com.horacioing.ivstore.application.usecases.store.DeleteStoreUseCaseImpl;
import com.horacioing.ivstore.application.usecases.store.RetrieveStoreUseCaseImpl;
import com.horacioing.ivstore.application.usecases.store.UpdateStoreUseCaseImpl;
import com.horacioing.ivstore.domain.ports.out.StoreRepositoryPort;
import com.horacioing.ivstore.infrastructure.adapters.mysql.StoreRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreBeansConfig {
    @Bean
    public StoreService storeService(StoreRepositoryPort storeRepositoryPort) {
        return new StoreService(
                new CreateStoreUseCaseImpl(storeRepositoryPort),
                new UpdateStoreUseCaseImpl(storeRepositoryPort),
                new DeleteStoreUseCaseImpl(storeRepositoryPort),
                new RetrieveStoreUseCaseImpl(storeRepositoryPort)
        );
    }

    @Bean
    public StoreRepositoryPort storeRepositoryPort(StoreRepositoryAdapter storeRepositoryAdapter) {
        return storeRepositoryAdapter;
    }
}
