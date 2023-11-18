package com.horacioing.ivstore.application.services;

import com.horacioing.ivstore.domain.models.employee.Employee;
import com.horacioing.ivstore.domain.ports.in.employee.CreateEmployeeUseCase;
import com.horacioing.ivstore.domain.ports.in.employee.RetrieveEmployeeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class EmployeeService implements CreateEmployeeUseCase, RetrieveEmployeeUseCase {

    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final RetrieveEmployeeUseCase retrieveEmployeeUseCase;

    @Override
    public Employee createEmployee(Employee employee) {
        Optional<Employee> employeeFound = this.retrieveEmployeeUseCase.getEmployee(employee.getDni());
        return employeeFound.orElseGet(() -> this.createEmployeeUseCase.createEmployee(employee));
    }

    @Override
    public Optional<Employee> getEmployee(Long dni) {
        return this.retrieveEmployeeUseCase.getEmployee(dni);
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pagination) {
        return this.retrieveEmployeeUseCase.getAllEmployees(pagination);
    }
}
