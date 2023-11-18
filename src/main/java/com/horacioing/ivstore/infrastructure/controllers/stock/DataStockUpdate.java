package com.horacioing.ivstore.infrastructure.controllers.stock;

import com.horacioing.ivstore.domain.models.product.Product;
import com.horacioing.ivstore.domain.models.store.Store;

public record DataStockUpdate(
        Long id,
        Integer quantity,
        Product product,
        Store store
) {
}
