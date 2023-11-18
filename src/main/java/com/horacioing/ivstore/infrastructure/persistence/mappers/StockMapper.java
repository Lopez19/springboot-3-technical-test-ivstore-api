package com.horacioing.ivstore.infrastructure.persistence.mappers;

import com.horacioing.ivstore.domain.models.stock.Stock;
import com.horacioing.ivstore.infrastructure.persistence.entities.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StockMapper extends GenericMapper<StockEntity, Stock>{
}
