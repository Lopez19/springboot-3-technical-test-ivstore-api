package com.horacioing.ivstore.infrastructure.persistence.mappers;

import com.horacioing.ivstore.domain.models.employee.Employee;
import com.horacioing.ivstore.infrastructure.persistence.entities.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper extends GenericMapper<EmployeeEntity, Employee>{
}
