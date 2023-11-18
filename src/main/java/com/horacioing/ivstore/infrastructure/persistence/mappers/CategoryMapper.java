package com.horacioing.ivstore.infrastructure.persistence.mappers;

import com.horacioing.ivstore.domain.models.category.Category;
import com.horacioing.ivstore.infrastructure.persistence.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper extends GenericMapper<CategoryEntity, Category>{
}
