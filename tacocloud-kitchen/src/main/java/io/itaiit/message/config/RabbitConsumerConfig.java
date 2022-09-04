package io.itaiit.message.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author itaiit
 * @date 2022/9/4 12:25
 */
@Slf4j
@Configuration
public class RabbitConsumerConfig {

    @Bean
    @ConditionalOnMissingBean(MessageConverter.class)
    public MessageConverter messageConverter() {
        log.info("加载的consumer中的messageConverter......");
        return new Jackson2JsonMessageConverter();
    }
}
