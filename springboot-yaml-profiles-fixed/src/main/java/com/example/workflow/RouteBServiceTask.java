package com.example.workflow;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class RouteBServiceTask implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        System.out.println("🚛 Processing payment via Route B");
    }
}