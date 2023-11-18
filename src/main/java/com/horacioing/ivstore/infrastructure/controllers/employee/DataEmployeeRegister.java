package com.horacioing.ivstore.infrastructure.controllers.employee;

import com.horacioing.ivstore.domain.models.role.Role;
import com.horacioing.ivstore.domain.models.store.Store;

public record DataEmployeeRegister(
        Long dni,
        String name,
        String phone,
        Store store,
        Role role
) {
}
