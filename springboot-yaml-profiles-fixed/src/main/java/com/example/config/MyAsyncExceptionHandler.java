package com.example.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.err.println("🔥 Caught async exception in method: " + method.getName());
        System.err.println("Exception: " + ex.getMessage());
    }
}