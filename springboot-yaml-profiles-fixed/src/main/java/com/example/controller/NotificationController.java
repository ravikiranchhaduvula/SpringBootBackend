package com.example.controller;

import com.example.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/user/{id}")
    public ResponseEntity<String> notify(@PathVariable String id) {
        notificationService.sendNotification(id); // runs in background
        return ResponseEntity.ok("Notification triggered for user: " + id);
    }
}
