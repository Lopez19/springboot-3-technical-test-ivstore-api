package com.horacioing.ivstore.domain.models.employee;

import com.horacioing.ivstore.domain.models.role.Role;
import com.horacioing.ivstore.domain.models.store.Store;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    private Long dni;
    private String name;
    private String phone;
    private Store store;
    private Role role;
}
