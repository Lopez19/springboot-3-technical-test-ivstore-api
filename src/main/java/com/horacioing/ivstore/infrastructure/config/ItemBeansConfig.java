package com.horacioing.ivstore.infrastructure.config;

import com.horacioing.ivstore.application.services.ItemService;
import com.horacioing.ivstore.application.usecases.item.CreateItemUseCaseImpl;
import com.horacioing.ivstore.application.usecases.item.RetrieveItemUseCaseImpl;
import com.horacioing.ivstore.domain.ports.out.ItemRepositoryPort;
import com.horacioing.ivstore.infrastructure.adapters.mysql.ItemRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemBeansConfig {
    @Bean
    public ItemService itemService(ItemRepositoryPort itemRepositoryPort){
        return new ItemService(
                new CreateItemUseCaseImpl(itemRepositoryPort),
                new RetrieveItemUseCaseImpl(itemRepositoryPort)
        );
    }

    @Bean
    public ItemRepositoryPort itemRepositoryPort(ItemRepositoryAdapter itemRepositoryAdapter) {
        return itemRepositoryAdapter;
    }
}
