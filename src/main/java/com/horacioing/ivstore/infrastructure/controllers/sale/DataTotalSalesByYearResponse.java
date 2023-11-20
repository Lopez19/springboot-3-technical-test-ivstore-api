package com.horacioing.ivstore.infrastructure.controllers.sale;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DataTotalSalesByYearResponse(
        Integer year,
        Integer totalSalesYear,
        BigDecimal totalAmountYear
) {
}
