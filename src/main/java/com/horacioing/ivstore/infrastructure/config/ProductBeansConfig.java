package com.horacioing.ivstore.infrastructure.config;

import com.horacioing.ivstore.application.services.ProductService;
import com.horacioing.ivstore.application.usecases.product.CreateProductUseCaseImpl;
import com.horacioing.ivstore.application.usecases.product.DeleteProductUseCaseImpl;
import com.horacioing.ivstore.application.usecases.product.RetrieveProductUseCaseImpl;
import com.horacioing.ivstore.application.usecases.product.UpdateProductUseCaseImpl;
import com.horacioing.ivstore.domain.ports.out.ProductRepositoryPort;
import com.horacioing.ivstore.infrastructure.adapters.mysql.ProductRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeansConfig {
    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(
                new CreateProductUseCaseImpl(productRepositoryPort),
                new DeleteProductUseCaseImpl(productRepositoryPort),
                new UpdateProductUseCaseImpl(productRepositoryPort),
                new RetrieveProductUseCaseImpl(productRepositoryPort)
        );
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(ProductRepositoryAdapter productRepositoryAdapter) {
        return productRepositoryAdapter;
    }
}
