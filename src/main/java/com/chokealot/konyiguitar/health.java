package com.chokealot.konyiguitar;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/health")
public class health {

    @GetMapping("")
    public ResponseEntity<Integer> healthCheck() {
        return ResponseEntity.ok().build();
    }

}
