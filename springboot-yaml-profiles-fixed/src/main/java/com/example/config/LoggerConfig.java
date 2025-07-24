package com.example.config;

import com.example.service.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;

@Configuration
public class LoggerConfig {

    @Bean
    public Logger activeLogger(Environment env, Map<String, Logger> loggerMap) {
        String loggerType = env.getProperty("myapp.logger-type");
        if (loggerType == null) {
            throw new IllegalArgumentException("❌ myapp.logger-type is missing from config!");
        }

        Logger logger = loggerMap.get(loggerType);
        if (logger == null) {
            throw new IllegalArgumentException("❌ No Logger bean found for type: " + loggerType);
        }

        System.out.println("✅ Injected logger: " + loggerType + " → " + logger.getClass().getSimpleName());
        return logger;
    }
}
