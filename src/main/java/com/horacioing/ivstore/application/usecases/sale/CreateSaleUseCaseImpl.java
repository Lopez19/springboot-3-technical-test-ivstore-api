package com.horacioing.ivstore.application.usecases.sale;

import com.horacioing.ivstore.domain.models.sale.Sale;
import com.horacioing.ivstore.domain.ports.in.sale.CreateSaleUseCase;
import com.horacioing.ivstore.domain.ports.out.SaleRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateSaleUseCaseImpl implements CreateSaleUseCase {

    private final SaleRepositoryPort saleRepositoryPort;

    @Override
    public Sale createSale(Sale sale) {
        return this.saleRepositoryPort.save(sale);
    }
}
