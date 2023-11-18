package com.horacioing.ivstore.domain.ports.in.product;

import com.horacioing.ivstore.domain.models.product.Product;

public interface CreateProductUseCase {
    Product createProduct(Product product);
}
