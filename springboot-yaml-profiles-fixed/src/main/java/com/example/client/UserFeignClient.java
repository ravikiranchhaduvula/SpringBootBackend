package com.example.client;

import com.example.DTO.UserDTO;
import com.example.config.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userFeign",
        url = "${external.user-service.base-url}",
        path = "/mock/external",
        configuration = FeignSupportConfig.class)   // <— attach decoder+retryer)   // common path prefix
public interface UserFeignClient {
    @GetMapping("/users/{id}")
    UserDTO getUser(@PathVariable("id") long id,
                    @RequestParam(name = "fail", required = false) Boolean fail,
                    @RequestParam(name = "delayMs", required = false) Integer delayMs);
}

