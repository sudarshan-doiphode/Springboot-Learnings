package com.darsh.main;

import com.darsh.service.ProductService;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {

        ProductService productService = new ProductService();

        CompletableFuture.supplyAsync(() -> productService.getProductById(1))
                .thenApply(product -> product.getName().toUpperCase())
                .handle((product, ex) -> {
                    if (ex != null) {
                        System.out.println("Product not found");
                        return "Default Product Name";
                    } else {
                        return product;
                    }
                })
                .thenAccept(System.out::println);  // Process the result or default value

        CompletableFuture.supplyAsync(() -> productService.getProducts())
                .thenApply(products -> products.stream()
                        .map(product -> product.getName().toUpperCase())
                        .toList())
                .handle((productName, ex) -> {
                    if (ex != null) {
                        System.out.println("An error occurred: " + ex.getMessage());
                        // Return a default or fallback value in case of an error
                        return "Default Product Name";
                    } else {
                        return productName;
                    }})
                .thenAccept(finalProductName -> {
                    System.out.println("Product name: " + finalProductName);
                });


        // Prevent the main thread from exiting immediately
        System.out.println("Executing in main thread");
        try {
            Thread.sleep(8000);  // Adjust the sleep time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

