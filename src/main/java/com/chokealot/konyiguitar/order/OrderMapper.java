package com.chokealot.konyiguitar.order;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toDTO(OrderEntity orderEntity);

    OrderEntity fromDTO(Order order);
}
