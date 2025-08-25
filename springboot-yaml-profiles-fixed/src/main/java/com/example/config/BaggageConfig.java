package com.example.config;

import brave.baggage.BaggageField;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class BaggageConfig {

    @Bean
    public List<BaggageField> baggageFields() {
        return Arrays.asList(
                BaggageField.create("tenantId"),
                BaggageField.create("requestId")
        );
    }
}

