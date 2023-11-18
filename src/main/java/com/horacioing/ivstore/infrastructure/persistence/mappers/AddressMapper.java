package com.horacioing.ivstore.infrastructure.persistence.mappers;

import com.horacioing.ivstore.domain.models.address.Address;
import com.horacioing.ivstore.infrastructure.persistence.entities.AddressEmb;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper extends GenericMapper<AddressEmb, Address>{
}
