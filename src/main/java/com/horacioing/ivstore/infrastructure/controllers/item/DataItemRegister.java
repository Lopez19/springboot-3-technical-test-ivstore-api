package com.horacioing.ivstore.infrastructure.controllers.item;

import com.horacioing.ivstore.domain.models.product.Product;
import com.horacioing.ivstore.domain.models.sale.Sale;
import com.horacioing.ivstore.domain.models.store.Store;

public record DataItemRegister(
        Sale sale,
        Product product,
        Integer quantity,
        Store store
) {
}
