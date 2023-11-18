package com.horacioing.ivstore.domain.models.store;

import com.horacioing.ivstore.domain.models.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Store {
    private Long id;
    private String name;
    private Address address;
    private String phone;
    private String color;
    private LocalDateTime createdAt;
}
