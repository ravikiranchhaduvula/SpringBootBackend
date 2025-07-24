package com.example.controller;

import com.example.model.User;
import com.example.service.TimeService;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

@RestController
@RequestMapping("/api")
@Slf4j
public class InitBinderController {

    private final UserService userService;
    private final TimeService timeService;

    public InitBinderController(@Lazy UserService userService, TimeService timeService) {
        this.userService = userService;
        this.timeService = timeService;
    }

    // This InitBinder trims all incoming strings
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                // Trim input strings, convert null if empty
                setValue(text == null ? null : text.trim());
            }
        });
    }

    // Request Param
    @GetMapping("/greet")
    public String greetUser(@RequestParam String name) {
        log.info("{}, {}", userService.getGreeting(), name);
        return "Hello, " + name + "!";
    }

    // Path Variable
    @GetMapping("/greet/{name}")
    public String greetPath(@PathVariable String name) {
        return "Hello, " + name + " (from path variable)";
    }

    // Post Mapping
    @PostMapping("/user")
    public String createUser(@RequestBody User user) {
        return "Received user: " + user.getUserName() + " with email: " + user.getEmail();
    }

    // Response Entity
    @GetMapping("/user/{name}")
    public ResponseEntity<String> getUserByName(@PathVariable String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Name must not be empty");
        }

        // Simulate a lookup or logic
        if (name.equalsIgnoreCase("ravi")) {
            return ResponseEntity.ok("User Ravi found");
        } else {
            return ResponseEntity.status(404).body("User not found: " + name);
        }
    }

    @GetMapping("/time")
    public String getTime() {
        return timeService.getCurrentTime();
    }
}

