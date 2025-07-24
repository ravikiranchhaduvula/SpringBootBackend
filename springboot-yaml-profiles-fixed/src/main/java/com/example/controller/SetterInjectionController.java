package com.example.controller;

import com.example.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/setter")
public class SetterInjectionController {
    private LoggingService loggingService;

    @Autowired // Fails If Setter Method is private
    public void setLoggingService(@Qualifier("activeLogger") LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @GetMapping("/log")
    public String logWithSetter(@RequestParam String message) {
        loggingService.log("[Setter] " + message);
        return "Setter Logged: " + message;
    }
}
