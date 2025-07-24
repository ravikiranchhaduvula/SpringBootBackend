package com.example.controller;

import com.example.service.Logger;
import com.example.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fail")
public class FailController {

    private final Logger logger;

    // @Autowired
    public FailController(@Qualifier("activeLogger") Logger logger) {
        this.logger = logger;
    }

    @GetMapping("/log")
    public String log(@RequestParam String msg) {
        logger.log(msg);
        return "Logged";
    }
}
