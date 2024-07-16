package com.graphql.controller;

import com.graphql.entity.Cricketer;
import com.graphql.service.CricketerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CricketerController {

    private final CricketerService cricketerService;

    @QueryMapping
    public List<Cricketer> getCricketers() {
        return cricketerService.getAllCricketers();
    }

    @QueryMapping
    public Cricketer getCricketer(@Argument int id) {
        return cricketerService.getCricketer(id);
    }

    @MutationMapping
    public Cricketer createCricketer(@Argument String name, @Argument String runs, @Argument String wickets) {
        return cricketerService.createCricketer(name, runs, wickets);
    }

    @MutationMapping
    public String deleteCricketer(@Argument int id) {
        return cricketerService.deleteCricketer(id);
    }

    @MutationMapping
    public Cricketer updateCricketer(@Argument int id, @Argument String name, @Argument String runs, @Argument String wickets) {
        return cricketerService.updateCricketer(id, name, runs, wickets);
    }

}
