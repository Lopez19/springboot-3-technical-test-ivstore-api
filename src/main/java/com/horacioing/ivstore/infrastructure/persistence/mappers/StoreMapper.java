package com.horacioing.ivstore.infrastructure.persistence.mappers;

import com.horacioing.ivstore.domain.models.store.Store;
import com.horacioing.ivstore.infrastructure.persistence.entities.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StoreMapper extends GenericMapper<StoreEntity, Store>{
}
