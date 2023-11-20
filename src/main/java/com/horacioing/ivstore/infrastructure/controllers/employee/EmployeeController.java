package com.horacioing.ivstore.infrastructure.controllers.employee;

import com.horacioing.ivstore.application.services.EmployeeService;
import com.horacioing.ivstore.domain.models.employee.Employee;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employ")
@AllArgsConstructor
@Tag(name = "Employee", description = "Employee API")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @RequestBody
            DataEmployeeRegister dataRegister,
            UriComponentsBuilder uriBuilder
    ) {
        Employee employeeSaved = this.employeeService.createEmployee(
                Employee.builder()
                        .dni(dataRegister.dni())
                        .name(dataRegister.name())
                        .phone(dataRegister.phone())
                        .store(dataRegister.store())
                        .role(dataRegister.role())
                        .build()
        );

        return ResponseEntity.created(
                uriBuilder.path("/api/v1/employ/{employeeId}")
                        .buildAndExpand(employeeSaved.getDni())
                        .toUri()
        ).body(employeeSaved);
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(Pageable pagination){
        return ResponseEntity.ok(this.employeeService.getAllEmployees(pagination));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable(name = "employeeId")
            Long id
    ) {
        Optional<Employee> employeeFound = this.employeeService.getEmployee(id);
        return employeeFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
