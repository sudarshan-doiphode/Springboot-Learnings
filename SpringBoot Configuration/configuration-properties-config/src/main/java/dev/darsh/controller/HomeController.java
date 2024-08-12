package dev.darsh.controller;

import dev.darsh.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private ApplicationConfig config;

    @GetMapping("/email")
    public ResponseEntity<String> getEmail() {
        return ResponseEntity.status(200)
                .body(config.getEmail());
    }

    @GetMapping("/address")
    public ResponseEntity<Map<String, String>> getAddress() {
        Map<String, String> address = config.getAddress();
        return ResponseEntity.status(200)
                .body(address);
    }

    @GetMapping("/mobile")
    public ResponseEntity<List<Long>> getMobileNumbers() {
        return ResponseEntity.status(200)
                .body(config.getMobile());
    }

    @GetMapping("/user")
    public ResponseEntity<ApplicationConfig> getUser() {
        return ResponseEntity.status(200)
                .body(config);
    }
}
