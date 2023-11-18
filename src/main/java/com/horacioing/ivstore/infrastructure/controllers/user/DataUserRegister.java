package com.horacioing.ivstore.infrastructure.controllers.user;

import com.horacioing.ivstore.domain.models.employee.Employee;

public record DataUserRegister(
        String email,
        String password,
        Employee employee
) {
}
