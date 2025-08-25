package com.example.config;

import brave.Tracer;                        // Brave tracer (provided by the bridge)
import brave.baggage.BaggageField;
import brave.propagation.TraceContext;      // Brave TraceContext
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TenantContext {

    private final Tracer braveTracer;         // <-- inject Brave's tracer

    private static final BaggageField TENANT_ID  = BaggageField.create("tenantId");
    private static final BaggageField REQUEST_ID = BaggageField.create("requestId");

    public void setTenant(String tenantId) {
        TraceContext ctx = currentBraveContext();
        if (ctx != null) {
            TENANT_ID.updateValue(ctx, tenantId);      // ✅ non-deprecated
        }
    }

    public void setRequestId(String requestId) {
        TraceContext ctx = currentBraveContext();
        if (ctx != null) {
            REQUEST_ID.updateValue(ctx, requestId);    // ✅ non-deprecated
        }
    }

    private TraceContext currentBraveContext() {
        var span = braveTracer.currentSpan();
        return (span != null) ? span.context() : null;  // Brave TraceContext directly
    }
}
