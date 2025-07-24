package com.example.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Qualifier("advancedLoggingService")
@ConditionalOnProperty(
        prefix = "myapp",
        name = "enable-advanced-logger",
        havingValue = "true",
        matchIfMissing = false // Optional: don't load by default
)
public class AdvancedLoggingService  implements Logger {
    @Override
    public void log(String msg) {
        System.out.println("AdvancedLogger: " + msg);
    }

    @PostConstruct
    public void init() {
        System.out.println("✅ AdvancedLoggingService bean initialized");
    }
}