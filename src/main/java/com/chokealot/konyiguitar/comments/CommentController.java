package com.chokealot.konyiguitar.comments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RequestMapping("api/v1/comments")
@Controller
public class CommentController {

    @Autowired
    CommentService service;

    Logger logger = LoggerFactory.getLogger(Logger.class);

    @GetMapping("")
    public ResponseEntity<Stream<Comment>> getAll() {
        try {
            logger.info("Getting list of comments");
            return ResponseEntity.ok(service.findAllComments());
        } catch (IllegalArgumentException e) {
            logger.warn("Cannot get list of comments");
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getOne(@PathVariable Long id) {
        try {
            logger.info("Getting comment with id "+id);
            return ResponseEntity.ok(service.findComment(id));
        } catch (IllegalArgumentException e) {
            logger.warn("Cannot find comment with id "+id);
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        try {
            logger.info("Creating a new comment");
            return ResponseEntity.ok(service.saveComment(comment));
        } catch (IllegalArgumentException e) {
            logger.warn("Cannot save comment!");
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        try {
            logger.info("Deleteing comment with id"+id);
            return ResponseEntity.ok(service.deleteComment(id));
        } catch (IllegalArgumentException e) {
            logger.warn("Cannot delete comment!");
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
