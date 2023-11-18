package com.horacioing.ivstore.domain.ports.in.sale;

import com.horacioing.ivstore.domain.models.sale.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RetrieveSaleUseCase {
    Optional<Sale> getSale(Long id);

    Page<Sale> getAllSales(Pageable pagination);
}
