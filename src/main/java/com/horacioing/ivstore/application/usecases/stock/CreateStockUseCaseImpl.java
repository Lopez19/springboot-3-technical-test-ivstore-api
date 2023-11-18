package com.horacioing.ivstore.application.usecases.stock;

import com.horacioing.ivstore.domain.models.stock.Stock;
import com.horacioing.ivstore.domain.ports.in.stock.CreateStockUseCase;
import com.horacioing.ivstore.domain.ports.out.StockRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CreateStockUseCaseImpl implements CreateStockUseCase {
    private final StockRepositoryPort stockRepositoryPort;

    @Override
    public Stock createStock(Stock stock) {
        Optional<Stock> stockFound = this.stockRepositoryPort.findByProductReference(stock.getProduct().getReference());
        return stockFound.orElseGet(() -> this.stockRepositoryPort.save(stock));
    }
}
