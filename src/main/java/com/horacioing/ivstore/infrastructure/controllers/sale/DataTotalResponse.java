package com.horacioing.ivstore.infrastructure.controllers.sale;

import lombok.Builder;

import java.util.List;

@Builder
public record DataTotalResponse(
        String nameStore,
        Integer totalSales,
        String color,
        Double totalSalesAmount,
        List<?> salesTotalGroupByMonth
) {
}
