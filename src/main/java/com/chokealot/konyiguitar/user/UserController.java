package com.chokealot.konyiguitar.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Stream;

@RequestMapping("/api/v1/users")
@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(Logger.class);

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            service.createUser(user);
            logger.info(user.getUsername()+" created!");
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        try {
            User user = service.getUser(username);
            logger.info("User ["+user.getUsername()+"] found!");
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            logger.warn("Error while trying to get a user by name");
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<Stream<User>> getAll() {
        try {
            Stream<User> stream = service.getAllUsers();
            logger.info("Stream of user's has been found");
            return ResponseEntity.ok(stream);
        } catch (IllegalArgumentException e) {
            logger.warn("Error while getting Stream of users");
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> delete(@PathVariable String username) {
        try {
            logger.info("User ["+username+"] has been deleted");
            return ResponseEntity.ok(service.deleteUser(username));
        } catch (Exception e) {
            logger.warn("Error while trying to delete");
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
