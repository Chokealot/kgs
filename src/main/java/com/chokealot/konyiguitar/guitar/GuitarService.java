package com.chokealot.konyiguitar.guitar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class GuitarService {

    @Autowired
    GuitarMapper mapper;

    GuitarRepository repository;

    public GuitarService(GuitarRepository repository) {
        this.repository = repository;
    }

    public Guitar create(Guitar guitar) {
        return mapper.toDTO(repository.save(mapper.fromDTO(guitar)));
    }

    public Guitar get(Long id) {
        return mapper.toDTO(repository.findById(id).get());
    }

    public Stream<Guitar> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDTO);
    }

    public String delete(Long id) {
        repository.deleteById(id);
        return "User deleted!";
    }
}
