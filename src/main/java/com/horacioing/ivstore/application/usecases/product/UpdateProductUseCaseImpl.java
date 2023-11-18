package com.horacioing.ivstore.application.usecases.product;

import com.horacioing.ivstore.domain.models.product.Product;
import com.horacioing.ivstore.domain.ports.in.product.UpdateProductUseCase;
import com.horacioing.ivstore.domain.ports.out.ProductRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Optional<Product> updateProduct(Product product) {
        return this.productRepositoryPort.update(product.getId(), product);
    }
}
