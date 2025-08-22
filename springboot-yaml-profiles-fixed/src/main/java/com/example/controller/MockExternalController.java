package com.example.controller;

import com.example.DTO.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mock/external")
public class MockExternalController {

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable long id,
                           @RequestParam(required = false) Boolean fail,
                           @RequestParam(required = false) Integer delayMs) {

        // Optional: simulate slowness to test "slowCall" metrics
        if (delayMs != null && delayMs > 0) {
            try { Thread.sleep(delayMs); } catch (InterruptedException ignored) {}
        }

        if (Boolean.TRUE.equals(fail)) {
            // Throw -> RestClient/Feign will raise an exception => Retry kicks in
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE,
                    "upstream down (simulated)"
            );
        }

        return new UserDTO(id, "MockUser-" + id, "mock"+id+"@example.com");
    }
}
