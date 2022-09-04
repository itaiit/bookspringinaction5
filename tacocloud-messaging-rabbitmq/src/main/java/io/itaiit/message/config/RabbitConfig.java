package io.itaiit.message.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author itaiit
 * @date 2022/9/4 11:49
 */
@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange exchange() {
        return ExchangeBuilder.directExchange("tacocloud.orders")
                .build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("tacocloud.order") // 默认会绑定到默认的Exchange上面
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .withQueueName();

    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
