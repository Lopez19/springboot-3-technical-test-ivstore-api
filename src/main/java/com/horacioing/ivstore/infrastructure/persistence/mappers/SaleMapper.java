package com.horacioing.ivstore.infrastructure.persistence.mappers;

import com.horacioing.ivstore.domain.models.sale.Sale;
import com.horacioing.ivstore.infrastructure.persistence.entities.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SaleMapper extends GenericMapper<SaleEntity, Sale>{
}
