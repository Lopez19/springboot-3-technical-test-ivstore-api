package com.horacioing.ivstore.application.services;

import com.horacioing.ivstore.domain.models.sale.Sale;
import com.horacioing.ivstore.domain.ports.in.sale.CreateSaleUseCase;
import com.horacioing.ivstore.domain.ports.in.sale.RetrieveSaleUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class SaleService implements CreateSaleUseCase, RetrieveSaleUseCase {
    private final CreateSaleUseCase createSaleUseCase;
    private final RetrieveSaleUseCase retrieveSaleUseCase;

    @Override
    public Sale createSale(Sale sale) {
        return this.createSaleUseCase.createSale(sale);
    }

    @Override
    public Optional<Sale> getSale(Long id) {
        return this.retrieveSaleUseCase.getSale(id);
    }

    @Override
    public Page<Sale> getAllSales(Pageable pagination) {
        return this.retrieveSaleUseCase.getAllSales(pagination);
    }
}
