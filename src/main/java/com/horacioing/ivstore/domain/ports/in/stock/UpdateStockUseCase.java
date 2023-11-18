package com.horacioing.ivstore.domain.ports.in.stock;

import com.horacioing.ivstore.domain.models.stock.Stock;

import java.util.Optional;

public interface UpdateStockUseCase {
    Optional<Stock> updateStock(Stock stock);
}
