package io.itaiit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

/**
 * 由于使用WebFlux之后，底层使用的Web容器不一定是Servlet容器，而之前的Spring Security使用的是Servlet的Filter机制
 * <p>
 * 在配置上有些许的区别
 *
 * @author itaiit
 * @date 2022/9/10 11:16
 */
@Slf4j
@Profile("webflux")
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    @Autowired
    private ReactiveUserDetailsService reactiveUserDetailsService;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        log.info("webflux security start....." + reactiveUserDetailsService);
        http.
//                httpBasic(new Customizer<ServerHttpSecurity.HttpBasicSpec>() {
//                    @Override
//                    public void customize(ServerHttpSecurity.HttpBasicSpec httpBasicSpec) {
////                        httpBasicSpec.authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("/login"))
//                        httpBasicSpec.
//                    }
//                }).
        authorizeExchange()
                .pathMatchers("/design", "/orders").hasAuthority("USER")
                .anyExchange().permitAll()
                .and()
                .formLogin()
                .loginPage("/login").requiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login"))
                .authenticationManager(reactiveAuthenticationManager())
                .and()
                .csrf().disable();
//                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler())
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(reactiveUserDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder());
        return authenticationManager;
    }
}
