package com.horacioing.ivstore.domain.ports.in.employee;

import com.horacioing.ivstore.domain.models.employee.Employee;

public interface CreateEmployeeUseCase {
    Employee createEmployee(Employee employee);
}
