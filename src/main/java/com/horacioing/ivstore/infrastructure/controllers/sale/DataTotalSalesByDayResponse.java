package com.horacioing.ivstore.infrastructure.controllers.sale;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DataTotalSalesByDayResponse(
        String today,
        Integer totalSales,
        BigDecimal totalAmount
) {
}
