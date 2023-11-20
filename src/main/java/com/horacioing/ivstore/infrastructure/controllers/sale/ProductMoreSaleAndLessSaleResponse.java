package com.horacioing.ivstore.infrastructure.controllers.sale;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductMoreSaleAndLessSaleResponse(
        String productName,
        Integer totalSales,
        BigDecimal totalAmountSales,
        Integer quantitySales
) {
}
