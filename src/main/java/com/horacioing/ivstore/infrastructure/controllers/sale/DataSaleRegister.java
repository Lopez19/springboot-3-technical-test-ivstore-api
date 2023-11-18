package com.horacioing.ivstore.infrastructure.controllers.sale;

import com.horacioing.ivstore.domain.models.employee.Employee;
import com.horacioing.ivstore.domain.models.item.Item;
import com.horacioing.ivstore.domain.models.store.Store;

import java.math.BigDecimal;
import java.util.List;

public record DataSaleRegister(
        BigDecimal total,
        Store store,
        Employee employee,
        List<Item> items
) {
}
