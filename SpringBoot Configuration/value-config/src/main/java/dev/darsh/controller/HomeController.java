package dev.darsh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("user.name")
    private String name;

    @GetMapping("/name")
    public ResponseEntity<String> getName() {
        return ResponseEntity.status(200)
                .body("Hello :" + name);
    }
}
