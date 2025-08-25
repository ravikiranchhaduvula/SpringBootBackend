package com.example.client;

import com.example.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

//@TimeLimiter requires a CompletableFuture/Mono. Use a thread-pool bulkhead and fail if the call exceeds 1s.
@Service
@RequiredArgsConstructor
public class RobustAsyncRestClientUserClient {

    private final RestClient rc;

    @io.github.resilience4j.retry.annotation.Retry(name = "extUserAsync")
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "extUserAsync", fallbackMethod = "fallbackAsync")
    @io.github.resilience4j.bulkhead.annotation.Bulkhead(name = "extUserAsync", type = io.github.resilience4j.bulkhead.annotation.Bulkhead.Type.THREADPOOL)
    @io.github.resilience4j.timelimiter.annotation.TimeLimiter(name = "extUserAsync")
    public java.util.concurrent.CompletableFuture<UserDTO> getUserAsync(long id, boolean fail, Integer delayMs) {
        return java.util.concurrent.CompletableFuture.supplyAsync(() ->
                rc.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/mock/external/users/{id}")
                                .queryParam("fail", fail)
                                .queryParam("delayMs", delayMs)
                                .build(id))
                        .retrieve()
                        .body(UserDTO.class)
        );
    }

    public java.util.concurrent.CompletableFuture<UserDTO> fallbackAsync(long id, boolean fail, Integer delayMs, Throwable t) {
        return java.util.concurrent.CompletableFuture.completedFuture(
                new UserDTO(id, "fallback-user", "fallback@example.com", false));
    }
}
