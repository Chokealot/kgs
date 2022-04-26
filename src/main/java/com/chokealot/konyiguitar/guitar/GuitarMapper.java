package com.chokealot.konyiguitar.guitar;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GuitarMapper {

    GuitarMapper INSTANCE = Mappers.getMapper(GuitarMapper.class);

    Guitar toDTO(GutiarEntity gutiarEntity);

    GutiarEntity fromDTO(Guitar guitar);
}
