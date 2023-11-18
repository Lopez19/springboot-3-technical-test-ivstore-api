package com.horacioing.ivstore.domain.ports.in.stock;

import com.horacioing.ivstore.domain.models.stock.Stock;

public interface CreateStockUseCase {
    Stock createStock(Stock stock);
}
