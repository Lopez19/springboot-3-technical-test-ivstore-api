package com.horacioing.ivstore.infrastructure.controllers.sale;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DataTotalSalesGroupByMonthResponse(
        String month,
        Integer totalSales,
        BigDecimal totalAmount
) {
}
