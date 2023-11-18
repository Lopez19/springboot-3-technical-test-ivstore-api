package com.horacioing.ivstore.infrastructure.controllers.store;

import com.horacioing.ivstore.domain.models.address.Address;

public record DataStoreUpdate(
        Long id,
        String name,
        String color,
        String phone,
        Address address
) {
}
