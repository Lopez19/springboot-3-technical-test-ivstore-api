package com.horacioing.ivstore.infrastructure.persistence.mappers;

import com.horacioing.ivstore.domain.models.role.Role;
import com.horacioing.ivstore.infrastructure.persistence.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper extends GenericMapper<RoleEntity, Role>{
}
