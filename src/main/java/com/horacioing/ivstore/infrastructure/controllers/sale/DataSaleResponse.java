package com.horacioing.ivstore.infrastructure.controllers.sale;


import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record DataSaleResponse(
        Long id,
        LocalDate createdAt,
        String employeeName,
        String storeName,
        List<DataItemsResponse> items,
        BigDecimal total
) {
}
