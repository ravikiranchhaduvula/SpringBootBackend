package com.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Async("customExecutor")
    public void sendNotification(String userId) {
        System.out.println("Sending notification to: " + userId +
                " on thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Done with: " + userId);
    }
}
