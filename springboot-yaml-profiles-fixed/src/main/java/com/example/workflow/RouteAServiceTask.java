package com.example.workflow;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RouteAServiceTask implements JavaDelegate {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void execute(DelegateExecution execution) {
        String payload = "RouteA selected for payment " + execution.getProcessInstanceId();
        kafkaTemplate.send("downstream-routeA", payload);
        System.out.println("📤 Sent to MQ (RouteA): " + payload);
    }
}
