package com.horacioing.ivstore.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "CategoryEntity")
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String color;
}
