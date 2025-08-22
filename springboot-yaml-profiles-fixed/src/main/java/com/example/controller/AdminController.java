package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdminController {

    /*@GetMapping("/admin/stats")
    @PreAuthorize("hasRole('ADMIN')")   // requires ROLE_ADMIN
    public String stats() {
        return "admin stats";
    }

    @PostMapping("/users/{id}/deactivate")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')") // multiple roles
    public String deactivate(@PathVariable Long id) {
        return "deactivated " + id;
    }

    @GetMapping("/public/pings")
    public String ping() { return "pong"; }

    @GetMapping("/api/me")
    public String me(@AuthenticationPrincipal org.springframework.security.core.userdetails.User u) {
        return (u == null) ? "anonymous" : u.getUsername();
    }*/

}

