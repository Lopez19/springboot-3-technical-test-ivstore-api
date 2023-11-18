package com.horacioing.ivstore.infrastructure.persistence.entities;

import com.horacioing.ivstore.domain.models.address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "StoreEntity")
@Table(name = "stores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    private AddressEmb address;
    private String phone;
    private String color;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
