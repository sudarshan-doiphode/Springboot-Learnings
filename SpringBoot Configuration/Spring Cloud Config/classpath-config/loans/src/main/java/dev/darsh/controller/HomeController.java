package dev.darsh.controller;

import dev.darsh.config.LoansConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final LoansConfig config;

    HomeController(LoansConfig config){
        this.config=config;
    }

    @GetMapping("/")
    public ResponseEntity<LoansConfig> loansDetails(){
        return ResponseEntity.status(200)
                .body(config);
    }
}
