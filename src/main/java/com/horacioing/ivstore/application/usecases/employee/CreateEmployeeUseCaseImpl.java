package com.horacioing.ivstore.application.usecases.employee;

import com.horacioing.ivstore.domain.models.employee.Employee;
import com.horacioing.ivstore.domain.ports.in.employee.CreateEmployeeUseCase;
import com.horacioing.ivstore.domain.ports.out.EmployeeRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CreateEmployeeUseCaseImpl implements CreateEmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    @Override
    public Employee createEmployee(Employee employee) {
        Optional<Employee> employeeFound = this.employeeRepositoryPort.findById(employee.getDni());
        return employeeFound.orElseGet(() -> this.employeeRepositoryPort.save(employee));
    }
}
