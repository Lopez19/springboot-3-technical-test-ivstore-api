package com.horacioing.ivstore.domain.ports.in.stock;

import com.horacioing.ivstore.domain.models.stock.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RetrieveStockUseCase {
    Optional<Stock> getStock(Long id);
    Page<Stock> getStocks(Pageable pageable);
    Optional<Stock> getStockByProductReference(String productReference);
    Integer reduceStockByProductReference(String productReference, Integer quantity);
}
