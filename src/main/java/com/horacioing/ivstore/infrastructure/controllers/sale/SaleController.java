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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/sales")
@AllArgsConstructor
@Tag(name = "Sales", description = "Sales API")
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
}
