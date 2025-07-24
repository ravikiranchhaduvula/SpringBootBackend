package com.example.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component // Automatically picked up by component scanning
public class UserService {
    public UserService() {
        System.out.println("Initializing UserService Bean");
    }

    public String getGreeting() {
        return "Hello from UserService (@Component)";
    }

    @PostConstruct
    public void init() {
        // Simulate post-construction setup
        System.out.println("PostConstruct: Initializing config for UserService...");
    }

    @PreDestroy
    public void cleanup() {
        // Cleanup logic
        System.out.println("PreDestroy: UserService is being destroyed. Cleaning up...");
    }
}
