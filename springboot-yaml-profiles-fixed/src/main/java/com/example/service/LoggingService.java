package com.example.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("consoleLogger")
//@Primary
public class LoggingService implements Logger {
    public void log(String msg) {
        System.out.println("LOG: " + msg);
    }
}
