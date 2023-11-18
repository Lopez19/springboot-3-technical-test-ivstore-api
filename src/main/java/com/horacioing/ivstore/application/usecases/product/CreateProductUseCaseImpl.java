package com.horacioing.ivstore.application.usecases.product;

import com.horacioing.ivstore.domain.models.product.Product;
import com.horacioing.ivstore.domain.ports.in.product.CreateProductUseCase;
import com.horacioing.ivstore.domain.ports.out.ProductRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productFound = this.productRepositoryPort.findByReference(product.getReference());
        return productFound.orElseGet(() -> this.productRepositoryPort.save(product));
    }
}
