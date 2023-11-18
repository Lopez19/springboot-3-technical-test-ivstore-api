package com.horacioing.ivstore.application.services;

import com.horacioing.ivstore.domain.models.stock.Stock;
import com.horacioing.ivstore.domain.ports.in.stock.CreateStockUseCase;
import com.horacioing.ivstore.domain.ports.in.stock.RetrieveStockUseCase;
import com.horacioing.ivstore.domain.ports.in.stock.UpdateStockUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class StockService implements CreateStockUseCase, UpdateStockUseCase, RetrieveStockUseCase {

    private final CreateStockUseCase createStockUseCase;
    private final UpdateStockUseCase updateStockUseCase;
    private final RetrieveStockUseCase retrieveStockUseCase;

    @Override
    public Stock createStock(Stock stock) {
        return this.createStockUseCase.createStock(stock);
    }

    @Override
    public Optional<Stock> getStock(Long id) {
        return this.retrieveStockUseCase.getStock(id);
    }

    @Override
    public Page<Stock> getStocks(Pageable pageable) {
        return this.retrieveStockUseCase.getStocks(pageable);
    }

    @Override
    public Optional<Stock> getStockByProductReference(String productReference) {
        return this.retrieveStockUseCase.getStockByProductReference(productReference);
    }

    @Override
    public Optional<Stock> updateStock(Stock stock) {
        return this.updateStockUseCase.updateStock(stock);
    }

    @Override
    public Integer reduceStockByProductReference(String productReference, Integer quantity) {
        return this.retrieveStockUseCase.reduceStockByProductReference(productReference, quantity);
    }
}
