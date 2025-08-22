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

    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "extUser", fallbackMethod = "fallback")
    @io.github.resilience4j.retry.annotation.Retry(name = "extUser")
    public UserDTO getUser(long id, boolean fail) {
        return rc.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/mock/external/users/{id}")
                        .queryParam("fail", fail)
                        .build(id))
                .retrieve()
                .body(UserDTO.class);
    }

    // Fallback MUST match signature + Throwable at the end
    public UserDTO fallback(long id, boolean fail, Throwable t) {
        log.warn("Fallback for id={}, fail={}, cause={} : {}", id, fail, t.getClass().getSimpleName(), t.getMessage());
        return new UserDTO(id, "fallback-user", "fallback@example.com");
    }
}

