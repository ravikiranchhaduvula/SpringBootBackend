package com.example.config;

import com.example.service.TimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean  //This will be manually registered in the Spring context.
    public TimeService timeService() {
        return new TimeService();
    }
}
