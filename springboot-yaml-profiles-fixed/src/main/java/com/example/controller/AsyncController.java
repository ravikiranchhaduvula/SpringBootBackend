package com.example.controller;

import com.example.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncController {

    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/api/async")
    public String triggerAsync() {
        asyncService.processAsyncTask(); // this runs in background
        return "Async task triggered";
    }

    @GetMapping("/api/async/future")
    public CompletableFuture<String> handleAsync(@RequestParam(defaultValue = "false") boolean fail) {
        return asyncService.fetchDataAsync(fail)
                .exceptionally(ex -> {
                    System.out.println("🔥 Exception caught in caller: " + ex.getMessage());
                    return "⚠️ Failed to fetch data";
                });
    }
}

