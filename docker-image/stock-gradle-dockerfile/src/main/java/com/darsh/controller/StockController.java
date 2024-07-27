package com.darsh.controller;

import com.darsh.model.MyStock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {

    @GetMapping("/stock")
    public ResponseEntity<MyStock> getStockInfo() {
        MyStock googleStock = MyStock.builder()
                .id(1)
                .name("Google")
                .price(200.00)
                .description("Nothing")
                .build();

        return ResponseEntity.status(200)
                .body(googleStock);
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<MyStock>> getAllStocks() {
        MyStock google = MyStock.builder()
                .id(1)
                .name("Google")
                .price(200.00)
                .description("Nothing")
                .build();

        MyStock apple = MyStock.builder()
                .id(2)
                .name("Apple")
                .price(300.00)
                .description("Nothing")
                .build();

        var list = List.of(google, apple);

        return ResponseEntity.status(200)
                .body(list);
    }


}
