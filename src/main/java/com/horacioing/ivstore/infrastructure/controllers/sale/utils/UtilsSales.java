package com.horacioing.ivstore.infrastructure.controllers.sale.utils;

import com.horacioing.ivstore.domain.models.sale.Sale;
import com.horacioing.ivstore.infrastructure.controllers.sale.DataTotalResponse;
import com.horacioing.ivstore.infrastructure.controllers.sale.DataTotalSalesGroupByMonthResponse;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UtilsSales {

    //
    public static DataTotalResponse mapToDataTotalResponse(String storeName, List<Sale> sales) {
        return DataTotalResponse.builder()
                .nameStore(storeName)
                .totalSales(sales.size())
                .totalSalesAmount(sales.stream()
                        .mapToDouble(sale -> sale.getTotal().doubleValue())
                        .sum()
                )
                .color(sales.stream()
                        .map(sale -> sale.getStore().getColor())
                        .findFirst()
                        .orElse("blue")
                )
                .salesTotalGroupByMonth(mapToDataTotalSalesGroupByMonth(sales))
                .build();
    }

    private static List<DataTotalSalesGroupByMonthResponse> mapToDataTotalSalesGroupByMonth(List<Sale> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.getCreateAt().getMonth().toString()))
                .entrySet()
                .stream()
                .map(entry -> DataTotalSalesGroupByMonthResponse.builder()
                        .month(entry.getKey())
                        .totalSales(entry.getValue().size())
                        .totalAmount(BigDecimal.valueOf(entry.getValue().stream()
                                .mapToDouble(sale -> sale.getTotal().doubleValue())
                                .sum())
                        )
                        .build()
                )
                .toList();
    }

    //
    public static List<DataTotalResponse> buildSalesDataRangeByStore(List<Sale> sales) {
        return sales.stream().collect(
                Collectors.groupingBy(sale -> sale.getStore().getName())
        ).entrySet().stream().map(entry -> DataTotalResponse.builder()
                .nameStore(entry.getKey())
                .totalSales((int) entry.getValue().stream().mapToDouble(
                        sale -> sale.getTotal().doubleValue()
                ).count())
                .totalSalesAmount(entry.getValue().stream().mapToDouble(
                        sale -> sale.getTotal().doubleValue()
                ).sum())
                .color(entry.getValue().stream().map(sale -> sale.getStore().getColor()).findFirst().orElseGet(() -> "blue"))
                .salesTotalGroupByMonth(entry.getValue().stream().collect(
                        Collectors.groupingBy(sale -> sale.getCreateAt().getMonth().toString())
                ).entrySet().stream().map(entryMonth -> DataTotalSalesGroupByMonthResponse.builder()
                        .month(entryMonth.getKey())
                        .totalSales((int) entryMonth.getValue().stream().mapToDouble(
                                sale -> sale.getTotal().doubleValue()
                        ).count())
                        .build()
                ).toList())
                .build()
        ).toList();
    }
}
