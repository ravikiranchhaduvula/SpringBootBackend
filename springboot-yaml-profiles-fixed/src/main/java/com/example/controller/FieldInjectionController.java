package com.example.controller;

import com.example.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/field")
public class FieldInjectionController {

    @Autowired
    @Qualifier("activeLogger")
    private LoggingService loggingService;

    @GetMapping("/log")
    public String logMessage(@RequestParam String message) {
        loggingService.log(message);
        return "Logged: " + message;
    }
}
