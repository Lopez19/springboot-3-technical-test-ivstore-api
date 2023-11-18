package com.horacioing.ivstore.infrastructure.persistence.mappers;

import com.horacioing.ivstore.domain.models.product.Product;
import com.horacioing.ivstore.infrastructure.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper extends GenericMapper<ProductEntity, Product>{
}
