package com.horacioing.ivstore.application.usecases.stock;

import com.horacioing.ivstore.domain.models.stock.Stock;
import com.horacioing.ivstore.domain.ports.in.stock.RetrieveStockUseCase;
import com.horacioing.ivstore.domain.ports.out.StockRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class RetrieveStockUseCaseImpl implements RetrieveStockUseCase {
    private final StockRepositoryPort stockRepositoryPort;

    @Override
    public Optional<Stock> getStock(Long id) {
        return this.stockRepositoryPort.findById(id);
    }

    @Override
    public Page<Stock> getStocks(Pageable pageable) {
        return this.stockRepositoryPort.findAll(pageable);
    }

    @Override
    public Optional<Stock> getStockByProductReference(String productReference) {
        return this.stockRepositoryPort.findByProductReference(productReference);
    }

    @Override
    public Integer reduceStockByProductReference(String productReference, Integer quantity) {
        return this.stockRepositoryPort.reduceStockByProductReference(productReference, quantity);
    }
}
