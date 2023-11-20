package com.horacioing.ivstore.infrastructure.controllers.sale;

import lombok.Builder;

import java.util.List;

@Builder
public record DataTotalSalesByDateRangeResponse(
        String dateStart,
        String dateEnd,
        DataTotalSalesResponse totalSalesForever,
        List<DataTotalResponse> salesByStore
) {
}
