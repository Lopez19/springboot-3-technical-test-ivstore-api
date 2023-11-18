package com.horacioing.ivstore.domain.models.item;

import com.horacioing.ivstore.domain.models.product.Product;
import com.horacioing.ivstore.domain.models.sale.Sale;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Item {
    private Long id;
    private Integer quantity;
    private Product product;
    private Sale sale;
}
