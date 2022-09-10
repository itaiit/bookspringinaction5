package io.itaiit.controller;

import io.itaiit.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author itaiit
 * @date 2022/9/9 23:15
 */
@Slf4j
@RestController
@RequestMapping("/reactor")
public class WebFluxRestController {

    @Autowired
    public WebClient webClient;

    @GetMapping("/design/{id}")
    public Mono<Taco> getIngredientById(@PathVariable("id") String id) {
//        Mono<Taco> tacoMono = webClient.get()
//                .uri("/design/{id}", id)
//                .retrieve()
//                .bodyToMono(Taco.class);

        Mono<Taco> tacoMono = webClient.get()
                .uri("/design/{id}", id)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is5xxServerError()) {
                        log.error("/design/{id} execute error: 5xx");
                        return Mono.<Taco>empty();
                    }
                    return clientResponse.bodyToMono(Taco.class);
                });
        return tacoMono;

    }

}
