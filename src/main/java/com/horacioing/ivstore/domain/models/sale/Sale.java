package com.horacioing.ivstore.domain.models.sale;

import com.horacioing.ivstore.domain.models.employee.Employee;
import com.horacioing.ivstore.domain.models.store.Store;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Sale {
    private Long id;
    private LocalDateTime createAt;
    private BigDecimal total;
    private Store store;
    private Employee employee;
}
