package com.horacioing.ivstore.infrastructure.persistence.mappers;

import com.horacioing.ivstore.domain.models.product.Size;
import com.horacioing.ivstore.infrastructure.persistence.entities.SizeEn;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SizeMapper extends GenericMapper<SizeEn, Size>{
}
