package com.chokealot.konyiguitar.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    UserMapper mapper;

    
    public User createUser(User user) {
        if (repository.findUserEntityByUsername(user.getUsername()) == null) {
            return mapper.toDTO(repository.save(mapper.fromDTO(user)));
        }
        return user;
    }

    public User getUser(String username) {
        return mapper.toDTO(repository.findUserEntityByUsername(username));
    }

    public Stream<User> getAllUsers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDTO);
    }

    public String deleteUser(String username) {
        if (repository.findUserEntityByUsername(username) != null) {
            repository.deleteUserEntitiesByUsername(username);
            return "User ["+username+"] deleted!";
        }
        return "Error! User not found!";
    }
}
