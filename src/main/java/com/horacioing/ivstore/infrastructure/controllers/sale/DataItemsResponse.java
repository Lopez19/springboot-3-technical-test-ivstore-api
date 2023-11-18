package com.horacioing.ivstore.infrastructure.controllers.sale;

import lombok.Builder;

@Builder
public record DataItemsResponse(
        String productName,
        String reference,
        Integer quantity

) {
}
