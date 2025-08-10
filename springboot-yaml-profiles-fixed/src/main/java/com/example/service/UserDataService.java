package com.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserDataService {

    @Async
    public CompletableFuture<String> fetchUserNameAsync() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1000); // simulate delay
            System.out.println("Fetched username");
            return "ravi.kiran";
        });
    }

    @Async
    public CompletableFuture<String> fetchUserEmailAsync(String username) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(800);
            System.out.println("Fetched email for " + username);
            return username + "@example.com";
        });
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
