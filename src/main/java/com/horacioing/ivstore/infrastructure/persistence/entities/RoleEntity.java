package com.horacioing.ivstore.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "RoleEntity")
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
