package com.horacioing.ivstore.application.usecases.stock;

import com.horacioing.ivstore.domain.models.stock.Stock;
import com.horacioing.ivstore.domain.ports.in.stock.UpdateStockUseCase;
import com.horacioing.ivstore.domain.ports.out.StockRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateStockUseCaseImpl implements UpdateStockUseCase {
    private final StockRepositoryPort stockRepositoryPort;

    @Override
    public Optional<Stock> updateStock(Stock stock) {
        return this.stockRepositoryPort.update(stock.getId(), stock);
    }
}
