package com.chokealot.konyiguitar.comments;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Autowired
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment toDTO(CommentEntity commentEntity);

    CommentEntity fromDTO(Comment comment);
}
