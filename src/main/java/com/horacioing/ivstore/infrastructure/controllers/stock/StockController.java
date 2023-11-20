package com.horacioing.ivstore.infrastructure.controllers.stock;

import com.horacioing.ivstore.application.services.StockService;
import com.horacioing.ivstore.domain.models.stock.Stock;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stock")
@AllArgsConstructor
@Tag(name = "Stock", description = "The Stock API")
@CrossOrigin(origins = "*")
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> create(
            @RequestBody
            DataStockRegister dataRegister,
            UriComponentsBuilder uriBuilder
    ) {
        Stock stockSaved = this.stockService.createStock(
                Stock.builder()
                        .quantity(dataRegister.quantity())
                        .product(dataRegister.product())
                        .store(dataRegister.store())
                        .build()
        );

        return ResponseEntity.created(
                uriBuilder.path("/api/v1/stock/{id}")
                        .buildAndExpand(stockSaved.getId())
                        .toUri()
        ).body(stockSaved);
    }

    @GetMapping("/reference/{productReference}")
    public ResponseEntity<Stock> getStockByProductReference(
            @PathVariable("productReference")
            String productReference
    ) {
        Optional<Stock> stockFound = this.stockService.getStockByProductReference(productReference);
        return stockFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Stock> update(
            @RequestBody
            DataStockUpdate dataUpdate
    ) {
        Optional<Stock> stockUpdated = this.stockService.updateStock(
                Stock.builder()
                        .id(dataUpdate.id())
                        .quantity(dataUpdate.quantity())
                        .product(dataUpdate.product())
                        .store(dataUpdate.store())
                        .build()
        );
        return stockUpdated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Stock>> getAllStocks(Pageable pagination) {
        return ResponseEntity.ok(this.stockService.getStocks(pagination));
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<Stock> getStockById(
            @PathVariable("stockId")
            Long stockId
    ) {
        Optional<Stock> stockFound = this.stockService.getStock(stockId);
        return stockFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
