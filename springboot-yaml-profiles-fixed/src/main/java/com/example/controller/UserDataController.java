package com.example.controller;

import com.example.service.UserDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class UserDataController {

    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("/api/async/user-info")
    public CompletableFuture<String> getUserInfo() {
        return userDataService.fetchUserNameAsync()
                .thenCompose(username ->
                        userDataService.fetchUserEmailAsync(username)
                                .thenApply(email ->
                                        "✅ Username: " + username + ", Email: " + email
                                )
                ).exceptionally(ex -> {
                    System.out.println("❌ Error in async chain: " + ex.getMessage());
                    return "⚠️ Failed to fetch user info";
                });
    }

    @GetMapping("/api/async/user-info2")
    public CompletableFuture<String> getUserInfo2() {
        CompletableFuture<String> nameFuture = userDataService.fetchUserNameAsync();
        CompletableFuture<String> emailFuture = userDataService.fetchUserEmailAsync("staticName");

        return nameFuture.thenCombine(emailFuture, (username, email) ->
                "User: " + username + ", Email: " + email
        );
    }
}
