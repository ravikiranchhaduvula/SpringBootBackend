package com.example.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CounterService {
    public CounterService() {
        System.out.println("📦 CounterService instance created: " + System.identityHashCode(this));
    }
}
