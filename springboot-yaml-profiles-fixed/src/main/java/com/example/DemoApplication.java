package com.example;

import com.example.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaAuditing
@SpringBootApplication
@EnableAsync
@EnableFeignClients
public class DemoApplication {
    public static void main(String[] args) throws InterruptedException {
        var context = SpringApplication.run(DemoApplication.class, args);
        AppConfig config = context.getBean(AppConfig.class);
        System.out.println("Active Profile App Name: " + config.getName());
        System.out.println("Features: " + config.getFeatures());


        // Simulate app running briefly then shutting down
        /*Thread.sleep(5000); // 5 seconds pause
        System.out.println("Shutting down Spring context...");
        context.close();*/ // ⬅️ This triggers @PreDestroy
    }
}
