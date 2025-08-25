// com.example.controller.TraceDebugController
package com.example.controller;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class TraceDebugController {
    private final Tracer tracer;
    public TraceDebugController(Tracer tracer) { this.tracer = tracer; }

    @GetMapping("/api/trace/whoami")
    public Map<String, String> whoAmI() {
        Span span = tracer.currentSpan();

        String traceId = (span != null && span.context() != null) ? span.context().traceId() : null;
        String spanId  = (span != null && span.context() != null) ? span.context().spanId()  : null;

        // MDC can legitimately be null if baggage wasn't set
        String tenant  = MDC.get("tenantId");
        String reqId   = MDC.get("requestId");

        Map<String, String> out = new LinkedHashMap<>();
        out.put("traceId",  Objects.toString(traceId,  "-"));
        out.put("spanId",   Objects.toString(spanId,   "-"));
        out.put("mdc.tenantId",  Objects.toString(tenant, "-"));
        out.put("mdc.requestId", Objects.toString(reqId, "-"));
        return out;
    }
}
