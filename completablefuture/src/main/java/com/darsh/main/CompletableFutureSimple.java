package com.darsh.main;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSimple {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Starts");
        CompletableFuture<String> completableFuture = slowTask();
        completableFuture.thenAccept(System.out::println);
        System.out.println("Method Ends");
        System.out.println("Main Ends");
    }

    public static CompletableFuture<String> fastTask() {
        System.out.println("Method Starts");
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("Hello from fast task");
        return completableFuture;
    }

    public static CompletableFuture<String> slowTask() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Thread.sleep(2000);
        completableFuture.complete("Hello from slow task");
        return completableFuture;
    }
}
