package com.example.controller;

import com.example.service.RequestScopedService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/request-scope")
public class RequestScopeController {

    private final RequestScopedService requestScopedService;

    public RequestScopeController(RequestScopedService requestScopedService) {
        this.requestScopedService = requestScopedService;
    }

    @GetMapping
    public String checkScope() {
        int hash = requestScopedService.getInstanceHash();
        return "RequestScopedService instance hash: " + hash;
    }
}
