package com.example.client;

import com.example.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateUserClient {
    private final RestTemplate rt;

    public RestTemplateUserClient(@Qualifier("legacyRestTemplate") RestTemplate rt) {
        this.rt = rt;
    }

    public UserDTO getUser(long id) {
        return rt.getForObject("http://localhost:8081/mock/external/users/{id}", UserDTO.class, id);
    }
}
