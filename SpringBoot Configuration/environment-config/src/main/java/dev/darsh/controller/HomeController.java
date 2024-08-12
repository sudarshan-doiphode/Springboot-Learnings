package dev.darsh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private Environment environment;

    @Value("${api_endpoint}")
    private String endpoint;

    @Autowired
    public HomeController(Environment environment) {
        this.environment = environment;
    }


    @GetMapping("/end")
    public ResponseEntity<String> get(){
        return ResponseEntity.status(200)
                .body(endpoint);
    }

    @GetMapping("/api-key")
    public ResponseEntity<String> getAPIKey(){
        return ResponseEntity.status(200)
                .body(environment.getProperty("API_KEY"));
    }
}
