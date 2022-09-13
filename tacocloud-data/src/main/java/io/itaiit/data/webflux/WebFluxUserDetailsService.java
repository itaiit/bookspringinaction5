package io.itaiit.data.webflux;

import io.itaiit.data.UserRepository;
import io.itaiit.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author itaiit
 * @date 2022/9/10 12:40
 */
@Slf4j
@Profile("webflux")
@Service
public class WebFluxUserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        log.info("webflux login username: " + username);
        Mono<User> byUsername = userRepository.findByUsername(username);

        return Mono.from(byUsername);
    }
}
