package com.example.workflow;

import com.example.DTO.RouteOption;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * | JavaDelegate Use Case                  | Example                                               |
 * | -------------------------------------- | ----------------------------------------------------- |
 * | Evaluate routing based on FX/time/cost | ✅ You just implemented that!                          |
 * | Call external APIs for real-time rates | `RestTemplate` or `WebClient` inside delegate         |
 * | Write result to DB or cache            | Inject a Spring `@Service` and call it                |
 * | Send response to MQ queue              | Use `RabbitTemplate`, `JmsTemplate`, or KafkaProducer |
 * | Trigger another process or event       | Use `runtimeService.startProcessInstanceByKey(...)`   |
 */

@Component
public class RouteDecisionDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("📦 Routing decision started");

        // Simulate scoring logic
        List<RouteOption> options = List.of(
                new RouteOption("RouteA", 5.0, 30, 1.2),
                new RouteOption("RouteB", 3.0, 60, 1.1),
                new RouteOption("RouteC", 4.5, 45, 1.3)
        );

        // Simple scoring function (customize as needed)
        RouteOption best = options.stream()
                .min(Comparator.comparingDouble(o -> o.getCost() * o.getTimeInSeconds() / o.getFxRate()))
                .orElseThrow();

        System.out.println("✅ Best route chosen: " + best.getRouteName());

        // Store result into process context to use in next task or gateway
        execution.setVariable("selectedRoute", best.getRouteName());
    }
}
