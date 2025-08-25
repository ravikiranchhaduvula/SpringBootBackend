package com.example.controller;

import com.example.DTO.UserDTO;
import com.example.client.WebClientUserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/http-demo")
@RequiredArgsConstructor
public class WebClientDemoController {

    private final WebClientUserClient wcClient;

    // Reactive endpoint (returns Mono)
    @GetMapping("/wc/{id}")
    public Mono<UserDTO> viaWebClientReactive(@PathVariable long id,
                                              @RequestParam(defaultValue = "false") boolean fail,
                                              @RequestParam(required = false) Integer delayMs) {
        return wcClient.getUserReactive(id, fail, delayMs);
    }

    // Blocking endpoint (returns concrete DTO)
    @GetMapping("/wc-blocking/{id}")
    public UserDTO viaWebClientBlocking(@PathVariable long id,
                                        @RequestParam(defaultValue = "false") boolean fail,
                                        @RequestParam(required = false) Integer delayMs) {
        return wcClient.getUserBlocking(id, fail, delayMs);
    }
}
