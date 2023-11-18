package com.horacioing.ivstore.domain.models.user;

import com.horacioing.ivstore.domain.models.employee.Employee;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
    private Long id;
    private String email;
    private String password;
    private Employee employee;
}
