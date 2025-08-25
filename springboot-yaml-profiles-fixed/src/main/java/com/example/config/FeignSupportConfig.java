package com.example.config;

import com.example.exception.RemoteServiceException;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignSupportConfig {

    @Bean
    public feign.Retryer feignRetryer() {
        // period = 200ms, maxPeriod = 1s, maxAttempts = 3 (1 + 2 retries)
        return new feign.Retryer.Default(200, 1000, 3);
    }

    @Bean
    public feign.codec.ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    static class CustomErrorDecoder implements feign.codec.ErrorDecoder {
        private final feign.codec.ErrorDecoder defaultDecoder = new feign.codec.ErrorDecoder.Default();

        @Override
        public Exception decode(String methodKey, feign.Response response) {
            int status = response.status();

            // Retryable: transient problems
            if (status == 429 || status == 503 || status == 504) {
                // RetryableException triggers the Feign Retryer
                return new feign.RetryableException(
                        status,
                        "Transient error (" + status + ") from " + methodKey,
                        response.request().httpMethod(),
                        (Long) null, // retry after (Date) if available; null = immediate per Retryer schedule
                        response.request());
            }

            // Auth/permission issues — don't retry
            if (status == 401 || status == 403) {
                return new RuntimeException("Unauthorized/Forbidden: " + status);
            }

            // Client errors — don't retry
            if (status >= 400 && status < 500) {
                return new RuntimeException("Client error " + status + " in " + methodKey);
            }

            // Fallback to Feign’s default
            return defaultDecoder.decode(methodKey, response);
        }
    }

    @Bean
    public ErrorDecoder problemDetailDecoder() {
        return (methodKey, response) -> {
            try (var body = response.body() != null ? response.body().asInputStream() : null) {
                String payload = body != null ? new String(body.readAllBytes()) : "";
                return new RemoteServiceException(response.status(), payload);
            } catch (Exception e) {
                return new RemoteServiceException(response.status(), "Upstream error");
            }
        };
    }

}

