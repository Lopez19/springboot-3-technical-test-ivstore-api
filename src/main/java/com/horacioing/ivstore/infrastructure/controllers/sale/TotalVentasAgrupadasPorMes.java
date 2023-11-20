package com.horacioing.ivstore.infrastructure.controllers.sale;

import lombok.Builder;

@Builder
public record TotalVentasAgrupadasPorMes(
        String mes,
        Double total
) {
}
