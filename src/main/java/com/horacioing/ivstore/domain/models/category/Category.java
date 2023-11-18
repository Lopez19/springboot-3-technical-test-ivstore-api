package com.horacioing.ivstore.domain.models.category;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Category {
    private Long id;
    private String name;
    private String description;
    private String color;
}
