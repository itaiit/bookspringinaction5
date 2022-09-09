package io.itaiit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author itaiit
 * @date 2022/9/9 23:24
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:8080/"); // 定义一个WebClient，使用基准的URI
    }

}
