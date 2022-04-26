package com.chokealot.konyiguitar.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Autowired
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDTO(UserEntity userEntity);

    UserEntity fromDTO(User user);
}
