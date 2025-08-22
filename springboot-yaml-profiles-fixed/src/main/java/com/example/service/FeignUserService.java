package com.example.service;

import com.example.DTO.UserDTO;
import com.example.client.UserFeignClient;
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
    public UserDTO getUser(long id, boolean fail) {
        return client.getUser(id, fail);
    }

    public UserDTO fallback(long id, boolean fail, Throwable t) {
        return new UserDTO(id, "fallback-user", "fallback@example.com");
    }
}

