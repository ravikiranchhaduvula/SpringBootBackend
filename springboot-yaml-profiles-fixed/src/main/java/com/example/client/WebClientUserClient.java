package com.example.client;

import com.example.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class WebClientUserClient {

    private final WebClient webClient;

    /** Reactive (non-blocking) version */
    public Mono<UserDTO> getUserReactive(long id, boolean fail, Integer delayMs) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/mock/external/users/{id}")
                        .queryParam("fail", fail)
                        .queryParam("delayMs", delayMs)
                        .build(id))
                .retrieve()
                .bodyToMono(UserDTO.class)
                .timeout(Duration.ofSeconds(2))         // fail fast if too slow
                .retryWhen(reactor.util.retry.Retry
                        .backoff(2, Duration.ofMillis(200)) // simple 2 retries with backoff
                        .filter(ex -> true));               // you can narrow which errors retry
    }

    /** Blocking convenience (only when you’re inside MVC code and don’t want reactive) */
    public UserDTO getUserBlocking(long id, boolean fail, Integer delayMs) {
        return getUserReactive(id, fail, delayMs).block(Duration.ofSeconds(3));
    }
}
