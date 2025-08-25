package com.example.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // --- Helper: enrich ProblemDetail with common metadata (timestamp, trace, baggage) ---
    private ProblemDetail enrich(ProblemDetail pd) {
        pd.setProperty("timestamp", Instant.now().toString());
        // If you’re using Micrometer/Brave, these MDC keys exist in logs; include for clients too:
        String traceId   = org.slf4j.MDC.get("traceId");
        String spanId    = org.slf4j.MDC.get("spanId");
        String tenantId  = org.slf4j.MDC.get("tenantId");   // set by your baggage interceptor
        String requestId = org.slf4j.MDC.get("requestId");  // set by your baggage interceptor
        if (traceId != null)   pd.setProperty("traceId", traceId);
        if (spanId != null)    pd.setProperty("spanId", spanId);
        if (tenantId != null)  pd.setProperty("tenantId", tenantId);
        if (requestId != null) pd.setProperty("requestId", requestId);
        return pd;
    }

    // --- Your custom business exceptions ---
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNotFound(ResourceNotFoundException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Resource not found");
        pd.setDetail(ex.getMessage());
        pd.setType(URI.create("https://docs.yourapp/errors/not-found"));
        return enrich(pd);
    }


    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ProblemDetail handleBusiness(BusinessRuleException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pd.setTitle("Business rule violation");
        pd.setDetail(ex.getMessage());
        pd.setType(URI.create("https://docs.yourapp/errors/business-rule"));
        return enrich(pd);
    }

    // --- Validation (DTO @Valid) ---
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleBeanValidation(MethodArgumentNotValidException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Validation failed");
        pd.setType(URI.create("https://docs.yourapp/errors/validation"));

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fe -> fieldErrors.put(fe.getField(), fe.getDefaultMessage()));
        pd.setProperty("errors", fieldErrors);
        return enrich(pd);
    }

    // --- Validation (@Validated on @RequestParam, @PathVariable) ---
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleConstraintViolations(ConstraintViolationException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Constraint violation");
        pd.setType(URI.create("https://docs.yourapp/errors/constraint-violation"));
        pd.setDetail(ex.getMessage());
        return enrich(pd);
    }

    // --- Spring Security access denied ---
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ProblemDetail handleAccessDenied(AccessDeniedException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        pd.setTitle("Access denied");
        pd.setDetail("You do not have permission to perform this action.");
        pd.setType(URI.create("https://docs.yourapp/errors/access-denied"));
        return enrich(pd);
    }

    // --- Pass-throughs (ResponseStatusException / ErrorResponseException) ---
    @ExceptionHandler({ResponseStatusException.class, ErrorResponseException.class})
    public ProblemDetail handleResponseStatus(Exception ex) {
        // Spring already has status + detail inside
        HttpStatus status = (ex instanceof ResponseStatusException rse)
                ? (HttpStatus) rse.getStatusCode()
                : HttpStatus.INTERNAL_SERVER_ERROR;

        ProblemDetail pd = ProblemDetail.forStatus(status);
        pd.setTitle("Request error");
        pd.setDetail(ex.getMessage());
        return enrich(pd);
    }

    // Fallback (unexpected errors) ---
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleUnexpected(Exception ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Internal server error");
        // In DEV you may include ex.getMessage(); hide in PROD if sensitive
        pd.setDetail("Unexpected error. Please contact support if this persists.");
        pd.setType(URI.create("https://docs.yourapp/errors/internal"));
        // Optional: add rootCause
        pd.setProperty("error", ex.getClass().getName());
        return enrich(pd);
    }
}
/*
if (user == null) throw new ResourceNotFoundException("User not found: " + id);
if (!ruleOk) throw new BusinessRuleException("Cannot assign role due to …");
 */