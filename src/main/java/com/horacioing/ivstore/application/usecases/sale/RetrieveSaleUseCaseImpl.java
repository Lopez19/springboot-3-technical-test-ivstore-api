package com.horacioing.ivstore.application.usecases.sale;

import com.horacioing.ivstore.domain.models.sale.Sale;
import com.horacioing.ivstore.domain.ports.in.sale.RetrieveSaleUseCase;
import com.horacioing.ivstore.domain.ports.out.SaleRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class RetrieveSaleUseCaseImpl implements RetrieveSaleUseCase {
    private final SaleRepositoryPort saleRepositoryPort;

    @Override
    public Optional<Sale> getSale(Long id) {
        return this.saleRepositoryPort.findById(id);
    }

    @Override
    public Page<Sale> getAllSales(Pageable pagination) {
        return this.saleRepositoryPort.findAll(pagination);
    }
}
