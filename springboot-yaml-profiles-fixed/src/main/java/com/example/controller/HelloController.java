package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    // Public endpoint — permitted in SecurityConfig
    @GetMapping("/public/ping")
    public String ping() { return "pong"; }

    // Auth-only endpoint (no role)
    @GetMapping("/me")
    public String me(@AuthenticationPrincipal UserDetails user) {
        return (user == null) ? "anonymous" : user.getUsername();
    }

    // Admin-only endpoint
    @GetMapping("/admin/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminStats() { return "admin-ok"; }

    // (Optional) a simple hello
    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal UserDetails user) {
        return "Hello, " + (user != null ? user.getUsername() : "anonymous") + "!";
    }
}
