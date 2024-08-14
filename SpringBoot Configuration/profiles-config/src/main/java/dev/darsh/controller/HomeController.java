package dev.darsh.controller;

import dev.darsh.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private ApplicationConfig config;

    @Value("${app.name}")
    private String appName;

    @GetMapping("/app")
    public ResponseEntity<String> getAppName(){
        return ResponseEntity.status(200)
                .body(appName);
    }

    @GetMapping("/user")
    public ResponseEntity<ApplicationConfig> getUser(){
        return ResponseEntity.status(200)
                .body(config);
    }
}
