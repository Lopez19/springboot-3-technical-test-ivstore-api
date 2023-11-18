package com.horacioing.ivstore.domain.ports.in.sale;

import com.horacioing.ivstore.domain.models.sale.Sale;

public interface CreateSaleUseCase {
    Sale createSale(Sale sale);
}
