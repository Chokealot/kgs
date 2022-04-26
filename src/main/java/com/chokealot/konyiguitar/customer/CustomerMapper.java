package com.chokealot.konyiguitar.customer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Autowired
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toDTO(CustomerEntity customerEntity);

    CustomerEntity fromDTO(Customer customer);
    
}
