package com.example.service;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedService {

    private final int instanceHash = System.identityHashCode(this);

    @PostConstruct
    public void init() {
        System.out.println("🆕 RequestScopedService created: " + instanceHash);
    }

    public int getInstanceHash() {
        return instanceHash;
    }
}
