package com.horacioing.ivstore.domain.ports.in.employee;

import com.horacioing.ivstore.domain.models.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RetrieveEmployeeUseCase {
    Optional<Employee> getEmployee(Long dni);
    Page<Employee> getAllEmployees(Pageable pagination);
}
