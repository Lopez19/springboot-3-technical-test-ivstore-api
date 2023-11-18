package com.horacioing.ivstore.infrastructure.config;

import com.horacioing.ivstore.application.services.SaleService;
import com.horacioing.ivstore.application.usecases.sale.CreateSaleUseCaseImpl;
import com.horacioing.ivstore.application.usecases.sale.RetrieveSaleUseCaseImpl;
import com.horacioing.ivstore.domain.ports.out.SaleRepositoryPort;
import com.horacioing.ivstore.infrastructure.adapters.mysql.SaleRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleBeansConfig {
    @Bean
    public SaleService saleService(SaleRepositoryPort saleRepositoryPort){
        return new SaleService(
                new CreateSaleUseCaseImpl(saleRepositoryPort),
                new RetrieveSaleUseCaseImpl(saleRepositoryPort)
        );
    }

    @Bean
    public SaleRepositoryPort saleRepositoryPort(SaleRepositoryAdapter saleRepositoryAdapter) {
        return saleRepositoryAdapter;
    }
}
