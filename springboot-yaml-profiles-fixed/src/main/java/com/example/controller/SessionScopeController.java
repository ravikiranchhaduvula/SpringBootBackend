package com.example.controller;

import com.example.service.SessionScopedService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session-scope")
public class SessionScopeController {

    private final SessionScopedService sessionService;

    public SessionScopeController(SessionScopedService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String checkSession() {
        return "SessionScopedService ID: " + sessionService.getSessionId();
    }
}

