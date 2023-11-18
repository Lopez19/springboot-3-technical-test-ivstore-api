package com.horacioing.ivstore.application.usecases.employee;

import com.horacioing.ivstore.domain.models.employee.Employee;
import com.horacioing.ivstore.domain.ports.in.employee.RetrieveEmployeeUseCase;
import com.horacioing.ivstore.domain.ports.out.EmployeeRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class RetrieveEmployeeUseCaseImpl implements RetrieveEmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    @Override
    public Optional<Employee> getEmployee(Long dni) {
        return this.employeeRepositoryPort.findById(dni);
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pagination) {
        return this.employeeRepositoryPort.findAll(pagination);
    }
}
