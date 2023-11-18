package com.horacioing.ivstore.domain.ports.in.product;

import com.horacioing.ivstore.domain.models.product.Product;

import java.util.Optional;

public interface UpdateProductUseCase {
    Optional<Product> updateProduct(Product product);
}
