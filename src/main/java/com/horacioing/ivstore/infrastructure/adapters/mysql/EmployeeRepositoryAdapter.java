package com.horacioing.ivstore.infrastructure.adapters.mysql;

import com.horacioing.ivstore.domain.models.employee.Employee;
import com.horacioing.ivstore.domain.ports.out.EmployeeRepositoryPort;
import com.horacioing.ivstore.infrastructure.persistence.entities.EmployeeEntity;
import com.horacioing.ivstore.infrastructure.persistence.mappers.EmployeeMapper;
import com.horacioing.ivstore.infrastructure.persistence.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepositoryPort {

    private final EmployeeRepository repository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity entity = this.repository.save(
                this.employeeMapper.toEntity(employee)
        );
        return this.employeeMapper.toDomain(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!this.repository.existsById(id)){
            return Boolean.FALSE;
        }
        this.repository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return this.repository.findById(id).map(this.employeeMapper::toDomain);
    }

    @Override
    public Page<Employee> findAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(this.employeeMapper::toDomain);
    }

    @Override
    public Optional<Employee> update(Long id, Employee employee) {
        return Optional.empty();
    }
}
