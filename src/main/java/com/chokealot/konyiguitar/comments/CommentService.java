package com.chokealot.konyiguitar.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class CommentService {

    @Autowired
    CommentRepository repository;
    @Autowired
    CommentMapper mapper;

    public Comment saveComment(Comment comment) {
        return mapper.toDTO(repository.save(mapper.fromDTO(comment)));
    }

    public Stream<Comment> findAllComments() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDTO);
    }

    public Comment findComment(Long id) {
        Comment foundComment = mapper.toDTO(repository.getById(id));
        return foundComment;
    }

    public Comment deleteComment(Long id) {
        Comment deletedComment = mapper.toDTO(repository.getById(id));
        repository.delete(repository.getById(id));
        return deletedComment;
    }
}
