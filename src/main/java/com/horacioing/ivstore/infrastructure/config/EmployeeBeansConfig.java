package com.horacioing.ivstore.infrastructure.config;

import com.horacioing.ivstore.application.services.EmployeeService;
import com.horacioing.ivstore.application.usecases.employee.CreateEmployeeUseCaseImpl;
import com.horacioing.ivstore.application.usecases.employee.RetrieveEmployeeUseCaseImpl;
import com.horacioing.ivstore.domain.ports.out.EmployeeRepositoryPort;
import com.horacioing.ivstore.infrastructure.adapters.mysql.EmployeeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeBeansConfig {
    @Bean
    public EmployeeService employeeService(EmployeeRepositoryPort employeeRepositoryPort){
        return new EmployeeService(
                new CreateEmployeeUseCaseImpl(employeeRepositoryPort),
                new RetrieveEmployeeUseCaseImpl(employeeRepositoryPort)
        );
    }

    @Bean
    public EmployeeRepositoryPort employeeRepositoryPort(EmployeeRepositoryAdapter employeeRepositoryAdapter){
        return employeeRepositoryAdapter;
    }
}
