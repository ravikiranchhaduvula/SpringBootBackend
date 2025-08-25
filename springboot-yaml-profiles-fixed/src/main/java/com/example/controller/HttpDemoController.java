package com.example.controller;

import com.example.DTO.UserDTO;
import com.example.client.RestClientUserClient;
import com.example.client.RestTemplateUserClient;
import com.example.client.RobustAsyncRestClientUserClient;
import com.example.service.FeignUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/http-demo")
@RequiredArgsConstructor
public class HttpDemoController {

    private final RestTemplateUserClient rtClient;
    private final RestClientUserClient rcClient;
    private final FeignUserService feignService;
    private final RobustAsyncRestClientUserClient robustAsyncRestClientUserClient;

    @GetMapping("/rt/{id}")
    public UserDTO viaRestTemplate(@PathVariable long id) { return rtClient.getUser(id); }

    @GetMapping("/rc/{id}")
    public UserDTO viaRestClient(@PathVariable long id,
                                 @RequestParam(defaultValue = "false") boolean fail,
                                 @RequestParam(required = false) Integer delayMs) {
        return rcClient.getUser(id, fail, delayMs);
    }

    @GetMapping("/feign/{id}")
    public UserDTO viaFeign(@PathVariable long id,
                            @RequestParam(defaultValue = "false") boolean fail) {
        return feignService.getUser(id, fail);
    }

    @GetMapping("/rc-async/{id}")
    public java.util.concurrent.CompletableFuture<UserDTO> viaRestClientAsync(
            @PathVariable long id,
            @RequestParam(defaultValue = "false") boolean fail,
            @RequestParam(required = false) Integer delayMs) {
        return robustAsyncRestClientUserClient.getUserAsync(id, fail, delayMs);
    }
}
