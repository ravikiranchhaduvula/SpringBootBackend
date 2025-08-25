package com.example.client;

import com.example.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestClientUserClient {
    private final RestClient rc;

    /*public UserDTO getUser(long id) {
        return rc.get()
                .uri("/mock/external/users/{id}", id)
                .retrieve()
                .body(UserDTO.class);
    }*/

    @io.github.resilience4j.retry.annotation.Retry(name = "extUserSync")
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "extUserSync", fallbackMethod = "fallback")
    @io.github.resilience4j.bulkhead.annotation.Bulkhead(name = "extUserSync", type = io.github.resilience4j.bulkhead.annotation.Bulkhead.Type.SEMAPHORE)
    public UserDTO getUser(long id, boolean fail, Integer delayMs) {
        return rc.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/mock/external/users/{id}")
                        .queryParam("fail", fail)
                        .queryParam("delayMs", delayMs)
                        .build(id))
                .retrieve()
                .body(UserDTO.class);
    }

    // MUST match: same return type, same params in same order + trailing Throwable
    private UserDTO fallback(long id, boolean fail, Integer delayMs, Throwable t) {
        // (optional) log t
        log.warn("Fallback for id={}, fail={}, cause={} : {}", id, fail, t.getClass().getSimpleName(), t.getMessage());
        return new UserDTO(id, "fallback-user", "fallback@example.com", false);
    }
}

