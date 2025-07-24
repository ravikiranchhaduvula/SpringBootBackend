package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("bean(activeLogger) && execution(* com.example.service.Logger.log(..))")
    public void beforeLogMethod(JoinPoint joinPoint) {
        System.out.println("🔍 AOP Before Advice: Calling method - " + joinPoint.getSignature().getName());
    }
}

