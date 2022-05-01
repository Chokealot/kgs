package com.chokealot.konyiguitar.guitar;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@Controller
@RequestMapping("/api/v1/guitars")
public class GuitarController {

    Logger logger = LoggerFactory.getLogger(Logger.class);

    GuitarService service;

    public GuitarController(GuitarService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Guitar> createGuitar(@RequestBody Guitar guitar) {
        try {
             Guitar guitarToCreate = service.create(guitar);
            logger.info("Guitar created");
            return ResponseEntity.ok(guitarToCreate);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guitar> getGuitar(@PathVariable Long id) {
        try {
            Guitar guitar = service.get(id);
            logger.info("Found guitar with id "+guitar.getId());
            return ResponseEntity.ok(guitar);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<Stream<Guitar>> getAll() {
        try {
            Stream<Guitar> stream = service.getAll();
            logger.info("Found stream of guitars");
            return ResponseEntity.ok(stream);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Guitar> deleteGuitar(@PathVariable Long id) {
        try {
            Guitar guitar = service.get(id);
            logger.info("Deleting guitar with id "+id);
            return ResponseEntity.ok(guitar);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
