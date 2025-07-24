package com.example.service;

import jakarta.annotation.PostConstruct;
import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedService {

    private final String sessionId = UUID.randomUUID().toString();

    @PostConstruct
    public void init() {
        System.out.println("🆕 SessionScopedService created with sessionId: " + sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }
}
