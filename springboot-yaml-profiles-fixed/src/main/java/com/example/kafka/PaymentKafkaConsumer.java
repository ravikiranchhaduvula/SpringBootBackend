package com.example.kafka;

import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentKafkaConsumer {

    @Autowired
    private RuntimeService runtimeService;

    @KafkaListener(topics = "payment-events", groupId = "payment-group")
    public void receive(String message) {
        System.out.println("🔁 Received from Kafka: " + message);
        // Trigger Activiti flow here if needed
        runtimeService.startProcessInstanceByKey("paymentRouting");
    }
}

