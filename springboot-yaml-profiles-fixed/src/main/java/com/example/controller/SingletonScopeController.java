package com.example.controller;

import com.example.service.CounterService;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/singleton")
//@Scope("prototype")
public class SingletonScopeController {

    @Autowired
    private CounterService service1;

    @Autowired
    private CounterService service2;

    @GetMapping("/check")
    public String checkSingleton() {
        int hash1 = System.identityHashCode(service1);
        int hash2 = System.identityHashCode(service2);

        return "Hash1: " + hash1 + ", Hash2: " + hash2 +
                (hash1 == hash2 ? " ✅ Same instance (singleton)" : " ❌ Different instances");
    }
}