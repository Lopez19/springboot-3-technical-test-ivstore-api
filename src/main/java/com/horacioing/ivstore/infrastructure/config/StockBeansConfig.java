package com.horacioing.ivstore.infrastructure.config;

import com.horacioing.ivstore.application.services.StockService;
import com.horacioing.ivstore.application.usecases.stock.CreateStockUseCaseImpl;
import com.horacioing.ivstore.application.usecases.stock.RetrieveStockUseCaseImpl;
import com.horacioing.ivstore.application.usecases.stock.UpdateStockUseCaseImpl;
import com.horacioing.ivstore.domain.ports.out.StockRepositoryPort;
import com.horacioing.ivstore.infrastructure.adapters.mysql.StockRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockBeansConfig {
    @Bean
    public StockService stockService(StockRepositoryPort stockRepositoryPort) {
        return new StockService(
                new CreateStockUseCaseImpl(stockRepositoryPort),
                new UpdateStockUseCaseImpl(stockRepositoryPort),
                new RetrieveStockUseCaseImpl(stockRepositoryPort)
        );
    }

    @Bean
    public StockRepositoryPort stockRepositoryPort(StockRepositoryAdapter stockRepositoryAdapter) {
        return stockRepositoryAdapter;
    }
}
