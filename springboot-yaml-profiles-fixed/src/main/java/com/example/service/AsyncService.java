package com.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Async
    public void processAsyncTask() {
        System.out.println("Running async task...");
        throw new RuntimeException("Something went wrong in async task!");
    }

    @Async
    public CompletableFuture<String> fetchDataAsync(boolean fail) {
        if (fail) {
            throw new RuntimeException("❌ Simulated async failure");
        }
        return CompletableFuture.completedFuture("✅ Data fetched successfully!");
    }
}
