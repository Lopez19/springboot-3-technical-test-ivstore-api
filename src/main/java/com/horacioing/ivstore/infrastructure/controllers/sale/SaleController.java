package com.horacioing.ivstore.infrastructure.controllers.sale;

import com.horacioing.ivstore.application.services.ItemService;
import com.horacioing.ivstore.application.services.SaleService;
import com.horacioing.ivstore.domain.models.item.Item;
import com.horacioing.ivstore.domain.models.sale.Sale;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/sales")
@AllArgsConstructor
@Tag(name = "Sales", description = "Sales API")
@CrossOrigin(origins = "*")
public class SaleController {

    private final SaleService saleService;
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<DataSaleResponse> create(
            @RequestBody
            DataSaleRegister dataRegister,
            UriComponentsBuilder uriBuilder
    ) {
        Sale saleSaved = this.saleService.createSale(
                Sale.builder()
                        .total(dataRegister.total())
                        .employee(dataRegister.employee())
                        .store(dataRegister.store())
                        .build()
        );

        List<DataItemsResponse> itemsResponseList = new ArrayList<>();

        dataRegister.items().forEach(item -> {
            this.itemService.createItem(
                    Item.builder()
                            .sale(saleSaved)
                            .product(item.getProduct())
                            .quantity(item.getQuantity())
                            .build()
            );

            itemsResponseList.add(
                    DataItemsResponse.builder()
                            .productName(item.getProduct().getName())
                            .quantity(item.getQuantity())
                            .reference(item.getProduct().getReference())
                            .build()
            );
        });


        return ResponseEntity.created(
                uriBuilder.path("/api/v1/sales/{id}")
                        .buildAndExpand(saleSaved.getId())
                        .toUri()
        ).body(
                DataSaleResponse.builder()
                        .id(saleSaved.getId())
                        .createdAt(saleSaved.getCreateAt())
                        .employeeName(saleSaved.getEmployee().getName())
                        .storeName(saleSaved.getStore().getName())
                        .items(itemsResponseList)
                        .total(saleSaved.getTotal())
                        .build()
        );
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<DataSaleResponse> getSaleById(
            @PathVariable(name = "saleId")
            Long id,
            Pageable pagination
    ) {
        Optional<Sale> saleFound = this.saleService.getSale(id);

        List<DataItemsResponse> itemsResponseList = new ArrayList<>();
        this.itemService.getAllItemsBySaleId(id, pagination).forEach(item -> itemsResponseList.add(
                DataItemsResponse.builder()
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .reference(item.getProduct().getReference())
                        .build()
        ));

        return saleFound.map(sale -> ResponseEntity.ok(
                DataSaleResponse.builder()
                        .id(sale.getId())
                        .createdAt(sale.getCreateAt())
                        .employeeName(sale.getEmployee().getName())
                        .storeName(sale.getStore().getName())
                        .total(sale.getTotal())
                        .items(itemsResponseList)
                        .build()
        )).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<Page<DataSaleResponse>> getAllSales(Pageable pagination) {
        List<DataItemsResponse> dataItemsResponsesList = new ArrayList<>();

        Page<Sale> sales = this.saleService.getAllSales(pagination).map(sale -> {
            this.itemService.getAllItemsBySaleId(sale.getId(), pagination).forEach(item -> dataItemsResponsesList.add(
                    DataItemsResponse.builder()
                            .productName(item.getProduct().getName())
                            .quantity(item.getQuantity())
                            .reference(item.getProduct().getReference())
                            .build()
            ));
            return sale;
        });

        return ResponseEntity.ok(sales.map(sale -> DataSaleResponse.builder()
                .id(sale.getId())
                .createdAt(sale.getCreateAt())
                .employeeName(sale.getEmployee().getName())
                .storeName(sale.getStore().getName())
                .items(dataItemsResponsesList)
                .total(sale.getTotal())
                .build())
        );
    }

    @GetMapping("/totalSalesGroupStore")
    public ResponseEntity<List<DataTotalResponse>> getTotalSalesGroupStore() {

        List<DataTotalResponse> dataTotalSalesGroupStoreResponsesList = new ArrayList<>();
        Random r = new Random();

        this.saleService.getAllSales(Pageable.unpaged()).stream().collect(
                Collectors.groupingBy(sale -> sale.getStore().getName())
        ).forEach((storeName, sales) -> dataTotalSalesGroupStoreResponsesList.add(
                        DataTotalResponse.builder()
                                .nameStore(storeName)
                                .totalSales((int) sales.stream().mapToDouble(
                                        sale -> sale.getTotal().doubleValue()
                                ).count())
                                .totalSalesAmount(sales.stream().mapToDouble(
                                        sale -> sale.getTotal().doubleValue()
                                ).sum())
//                        .color("#" + Integer.toHexString((r.nextInt() * 16777215)))
                                .color(sales.stream().map(sale -> sale.getStore().getColor()).findFirst().orElseGet(() -> "blue"))
                                .salesForDate(sales.stream().map(sale -> sale.getCreateAt().toLocalDate()).toList())
                                .salesForMonth(sales.stream().map(sale -> sale.getCreateAt().getMonth()).toList())
                                .salesForYear(sales.stream().map(sale -> sale.getCreateAt().getYear()).toList())
                                .salesTotalGroupByMonth(sales.stream().collect(
                                        Collectors.groupingBy(sale -> sale.getCreateAt().getMonth().toString())
                                ).entrySet().stream().map(entry -> DataTotalSalesGroupByMonthResponse.builder()
                                        .month(entry.getKey())
                                        .totalSales((int) entry.getValue().stream().mapToDouble(
                                                sale -> sale.getTotal().doubleValue()
                                        ).count())
                                        .build()
                                ).toList())
                                .build()
                )
        );
        return ResponseEntity.ok(dataTotalSalesGroupStoreResponsesList);
    }

    @GetMapping("/totalSalesGroupMonth")
    public ResponseEntity<List<DataTotalSalesGroupByMonthResponse>> getTotalSalesGroupMonth() {

        List<DataTotalSalesGroupByMonthResponse> dataTotalSalesGroupByMonthResponsesList = new ArrayList<>();

//        this.saleService.getAllSales(Pageable.unpaged()).stream().collect(
//                Collectors.groupingBy(sale -> sale.getCreateAt().getMonth().toString())
//        ).forEach((month, sales) -> dataTotalSalesGroupByMonthResponsesList.add(
//                        DataTotalSalesGroupByMonthResponse.builder()
//                                .month(month)
//                                .totalSales((int) sales.stream().mapToDouble(
//                                        sale -> sale.getTotal().doubleValue()
//                                ).count())
//                                .build()
//                )
//        );

        //Si no hay ventas en un mes, se agrega el mes con total de ventas 0
        Stream.of(Month.values()).forEach(month -> {

            List<Sale> sales = this.saleService.getAllSales(Pageable.unpaged())
                    .stream().filter(sale ->
                            sale.getCreateAt().getMonth().toString().equalsIgnoreCase(month.toString())
                    ).toList();

            if (sales.isEmpty()) {
                dataTotalSalesGroupByMonthResponsesList.add(
                        DataTotalSalesGroupByMonthResponse.builder()
                                .month(month.name())
                                .totalSales(0)
                                .totalAmount(BigDecimal.valueOf(0))
                                .build()
                );
            } else {
                dataTotalSalesGroupByMonthResponsesList.add(
                        DataTotalSalesGroupByMonthResponse.builder()
                                .month(month.name())
                                .totalSales((int) sales.stream().mapToDouble(
                                        sale -> sale.getTotal().doubleValue()
                                ).count())
                                .totalAmount(BigDecimal.valueOf(sales.stream().mapToDouble(
                                        sale -> sale.getTotal().doubleValue()
                                ).sum()))
                                .build()
                );
            }

        });
        return ResponseEntity.ok(dataTotalSalesGroupByMonthResponsesList);
    }

    @GetMapping("/totalSalesActualMonth")
    public ResponseEntity<DataTotalSalesGroupByMonthResponse> getTotalSalesMonth() {
        //Traer el mes actual
        String month = LocalDateTime.now().getMonth().toString();

        //Traer las ventas del mes actual
        List<Sale> sales = this.saleService.getAllSales(Pageable.unpaged())
                .stream()
                .filter(sale ->
                        sale.getCreateAt().getMonth().toString()
                                .equals(month)
                ).toList();

        return ResponseEntity.ok(
                DataTotalSalesGroupByMonthResponse.builder()
                        .month(month)
                        .totalSales((int) sales.stream().mapToDouble(
                                sale -> sale.getTotal().doubleValue()
                        ).count())
                        .totalAmount(BigDecimal.valueOf(sales.stream().mapToDouble(
                                sale -> sale.getTotal().doubleValue()
                        ).sum()))
                        .build()
        );
    }

    @GetMapping("/totalSalesByDay")
    public ResponseEntity<DataTotalSalesByDayResponse> getTotalSalesByDay() {

        String today = LocalDateTime.now().toLocalDate().toString();

        //Traer las ventas del día actual
        List<Sale> sales = this.saleService.getAllSales(Pageable.unpaged())
                .stream()
                .filter(sale ->
                        sale.getCreateAt().toLocalDate().toString()
                                .equalsIgnoreCase(today)
                ).toList();

        return ResponseEntity.ok(
                DataTotalSalesByDayResponse.builder()
                        .today(today)
                        .totalSales((int) sales.stream().mapToDouble(
                                sale -> sale.getTotal().doubleValue()
                        ).count())
                        .totalAmount(BigDecimal.valueOf(sales.stream().mapToDouble(
                                sale -> sale.getTotal().doubleValue()
                        ).sum()))
                        .build()
        );
    }

    @GetMapping("/totalSales")
    public ResponseEntity<DataTotalSalesResponse> getTotalSales() {

        //Traer las ventas del día actual
        List<Sale> sales = this.saleService.getAllSales(Pageable.unpaged())
                .stream()
                .toList();

        return ResponseEntity.ok(
                DataTotalSalesResponse.builder()
                        .totalSalesForever((int) sales.stream().mapToDouble(
                                sale -> sale.getTotal().doubleValue()
                        ).count())
                        .totalAmountForever(BigDecimal.valueOf(sales.stream().mapToDouble(
                                sale -> sale.getTotal().doubleValue()
                        ).sum()))
                        .build()
        );
    }

    @GetMapping("/totalSalesByYear")
    public ResponseEntity<DataTotalSalesByYearResponse> getTotalSalesByYear() {

        int year = LocalDateTime.now().getYear();

        //Traer las ventas del año actual
        List<Sale> sales = this.saleService.getAllSales(Pageable.unpaged())
                .stream()
                .filter(sale ->
                        sale.getCreateAt().getYear() == year
                ).toList();

        return ResponseEntity.ok(
                DataTotalSalesByYearResponse.builder()
                        .year(year)
                        .totalSalesYear((int) sales.stream().mapToDouble(
                                sale -> sale.getTotal().doubleValue()
                        ).count())
                        .totalAmountYear(BigDecimal.valueOf(sales.stream().mapToDouble(
                                sale -> sale.getTotal().doubleValue()
                        ).sum()))
                        .build()
        );

    }

    @GetMapping("/productsMoreSoldAndLessSold")
    public ResponseEntity<List<ProductMoreSaleAndLessSaleResponse>> getProductMoreSoldAndLessSold() {

        List<ProductMoreSaleAndLessSaleResponse> productMoreSaleAndLessSaleResponsesList = new ArrayList<>();

        this.itemService.getAllItems(Pageable.unpaged()).stream().collect(
                Collectors.groupingBy(item -> item.getProduct().getName())
        ).forEach((productName, items) -> productMoreSaleAndLessSaleResponsesList.add(
                ProductMoreSaleAndLessSaleResponse.builder()
                        .productName(productName)
                        .totalSales((int) items.stream().mapToDouble(
                                item -> item.getQuantity().doubleValue()
                        ).count())
                        .totalAmountSales(BigDecimal.valueOf(items.stream().mapToDouble(
                                item -> item.getQuantity().doubleValue()
                                        * item.getProduct().getPrice().doubleValue()
                        ).sum()))
                        .quantitySales(items.stream().mapToInt(
                                Item::getQuantity
                        ).sum())
                        .build()
        ));

        productMoreSaleAndLessSaleResponsesList.sort((o1, o2) ->
                o2.quantitySales().compareTo(o1.quantitySales())
        );

        return ResponseEntity.ok(productMoreSaleAndLessSaleResponsesList);
    }

    @GetMapping("/totalSalesByDateRange")
    public ResponseEntity<DataTotalSalesByDateRangeResponse> getTotalSalesByDateRange(
            @RequestParam(name = "startDate")
            String startDate,
            @RequestParam(name = "endDate", required = false)
            String endDate
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        List<Sale> sales;

        if (endDate == null) {
            sales = this.saleService.getAllSales(Pageable.unpaged())
                    .stream()
                    .filter(sale ->
                            sale.getCreateAt().toLocalDate().isEqual(LocalDateTime.parse(startDate, formatter).toLocalDate())
                    )
                    .toList();

            return ResponseEntity.ok(
                    DataTotalSalesByDateRangeResponse.builder()
                            .dateStart(startDate)
                            .dateEnd(null)
                            .totalSalesForever(
                                    DataTotalSalesResponse.builder()
                                            .totalSalesForever((int) sales.stream().mapToDouble(
                                                    sale -> sale.getTotal().doubleValue()
                                            ).count())
                                            .totalAmountForever(BigDecimal.valueOf(sales.stream().mapToDouble(
                                                    sale -> sale.getTotal().doubleValue()
                                            ).sum()))
                                            .build())
                            .salesByStore(
                                    sales.stream().collect(
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
                                            .salesForDate(entry.getValue().stream().map(sale -> sale.getCreateAt().toLocalDate()).toList())
                                            .salesForMonth(entry.getValue().stream().map(sale -> sale.getCreateAt().getMonth()).toList())
                                            .salesForYear(entry.getValue().stream().map(sale -> sale.getCreateAt().getYear()).toList())
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
                                    ).toList()
                            ).build()
            );

        } else {
            sales = this.saleService.getAllSales(Pageable.unpaged())
                    .stream()
                    .filter(sale ->
                            sale.getCreateAt().toLocalDate().isAfter(LocalDateTime.parse(startDate, formatter).toLocalDate())
                                    && sale.getCreateAt().toLocalDate().isBefore(LocalDateTime.parse(endDate, formatter).toLocalDate())
                    ).toList();

            return ResponseEntity.ok(
                    DataTotalSalesByDateRangeResponse.builder()
                            .dateStart(startDate)
                            .dateEnd(endDate)
                            .totalSalesForever(
                                    DataTotalSalesResponse.builder()
                                            .totalSalesForever((int) sales.stream().mapToDouble(
                                                    sale -> sale.getTotal().doubleValue()
                                            ).count())
                                            .totalAmountForever(BigDecimal.valueOf(sales.stream().mapToDouble(
                                                    sale -> sale.getTotal().doubleValue()
                                            ).sum()))
                                            .build())
                            .salesByStore(
                                    sales.stream().collect(
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
                                            .salesForDate(entry.getValue().stream().map(sale -> sale.getCreateAt().toLocalDate()).toList())
                                            .salesForMonth(entry.getValue().stream().map(sale -> sale.getCreateAt().getMonth()).toList())
                                            .salesForYear(entry.getValue().stream().map(sale -> sale.getCreateAt().getYear()).toList())
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
                                    ).toList()
                            )
                            .build()
            );
        }
    }
}
