package com.darsh.controller;

import com.darsh.model.request.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationsController {

    @PostMapping("/receive")
    public String receiveUser(@Valid @RequestBody User user) {
        return "Hello " + user.getName() + " 🙂";
    }
}
