package com.horacioing.ivstore.domain.models.stock;

import com.horacioing.ivstore.domain.models.product.Product;
import com.horacioing.ivstore.domain.models.store.Store;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Stock {
    private Long id;
    private Product product;
    private Integer quantity;
    private Store store;
}
