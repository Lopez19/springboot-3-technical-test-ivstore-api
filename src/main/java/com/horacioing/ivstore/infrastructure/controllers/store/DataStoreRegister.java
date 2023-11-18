package com.horacioing.ivstore.infrastructure.controllers.store;

import com.horacioing.ivstore.domain.models.address.Address;

public record DataStoreRegister(
        String name,
        String phone,
        String color,
        Address address
) {
}
