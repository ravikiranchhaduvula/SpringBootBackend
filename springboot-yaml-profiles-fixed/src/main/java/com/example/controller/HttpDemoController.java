package com.example.controller;

import com.example.DTO.UserDTO;
import com.example.client.RestClientUserClient;
import com.example.client.RestTemplateUserClient;
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

    @GetMapping("/rt/{id}")
    public UserDTO viaRestTemplate(@PathVariable long id) { return rtClient.getUser(id); }

    @GetMapping("/rc/{id}")
    public UserDTO viaRestClient(@PathVariable long id,
                                 @RequestParam(defaultValue = "false") boolean fail) {
        return rcClient.getUser(id, fail);
    }

    @GetMapping("/feign/{id}")
    public UserDTO viaFeign(@PathVariable long id,
                            @RequestParam(defaultValue = "false") boolean fail) {
        return feignService.getUser(id, fail);
    }
}
