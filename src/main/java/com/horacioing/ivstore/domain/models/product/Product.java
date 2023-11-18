package com.horacioing.ivstore.domain.models.product;

import com.horacioing.ivstore.domain.models.category.Category;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Product {
    private Long id;
    private String name;
    private String description;
    private String color;
    private Category category;
    private Size size;
    private String reference;
    private BigDecimal price;
}
