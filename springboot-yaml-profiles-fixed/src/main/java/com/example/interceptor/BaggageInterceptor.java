// com.example.interceptor.BaggageInterceptor
package com.example.interceptor;

import io.micrometer.tracing.BaggageManager;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class BaggageInterceptor implements HandlerInterceptor {

    private final Tracer tracer;                 // ensures we have a current span/context
    private final BaggageManager baggageManager; // Micrometer abstraction over Brave baggage

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        String tenant   = req.getHeader("X-Tenant-Id");
        String requestId= req.getHeader("X-Request-Id");

        // Write into Micrometer baggage (propagates & can map to MDC)
        if (tenant != null && !tenant.isBlank()) {
            baggageManager.getBaggage("tenantId").set(tenant);
        }
        if (requestId != null && !requestId.isBlank()) {
            baggageManager.getBaggage("requestId").set(requestId);
        }

        // Optional: immediate MDC so your logs on this thread show the values even
        // before the correlation bridge copies them (harmless duplication)
        if (tenant != null)   MDC.put("tenantId", tenant);
        if (requestId != null)MDC.put("requestId", requestId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) {
        // Prevent MDC leakage across requests/threads
        MDC.remove("tenantId");
        MDC.remove("requestId");
    }
}
