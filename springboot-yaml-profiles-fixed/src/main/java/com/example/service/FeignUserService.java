package com.example.service;

import com.example.DTO.UserDTO;
import com.example.client.UserFeignClient;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeignUserService {
    private final UserFeignClient client;

    /*public UserDTO getUser(long id) {
        return client.getUser(id);
    }*/
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "extUser",
            fallbackMethod = "fallback")
    @io.github.resilience4j.retry.annotation.Retry(name = "extUser")
    @RateLimiter(name = "extUserRl", fallbackMethod = "rateLimitedFallback") // NEW
    public UserDTO getUser(long id, boolean fail) {
        return client.getUser(id, fail, null);
    }

    public UserDTO fallback(long id, boolean fail, Throwable t) {
        return new UserDTO(id, "fallback-user", "fallback@example.com", false);
    } // RateLimiter fallback (called when no permit is available within timeoutDuration)

    public UserDTO rateLimitedFallback(long id, boolean fail, Integer delayMs, Throwable t) {
        // you can log t (RateLimitExceededException) and return a friendly response
        return new UserDTO(id, "rate-limited", "too-many-requests@example.com", false);
    }
}

