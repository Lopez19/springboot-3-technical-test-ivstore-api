package com.horacioing.ivstore.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "UserEntity")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @OneToOne(targetEntity = EmployeeEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_dni")
    private EmployeeEntity employee;
}
