package io.itaiit.message.impl;

import io.itaiit.domain.Order;
import io.itaiit.message.OrderMessagingService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author itaiit
 * @date 2022/9/4 11:41
 */
@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendOrder(Order order) {
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        MessageProperties messageProperties = new MessageProperties();
        Message message = messageConverter.toMessage(order, messageProperties);
        rabbitTemplate.send("tacocloud.order", message);
    }
}
