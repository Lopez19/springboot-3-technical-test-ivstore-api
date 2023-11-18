package com.horacioing.ivstore.infrastructure.persistence.mappers;

import com.horacioing.ivstore.domain.models.item.Item;
import com.horacioing.ivstore.infrastructure.persistence.entities.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemMapper extends GenericMapper<ItemEntity, Item>{
}
