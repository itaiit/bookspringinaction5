package io.itaiit.message;

import io.itaiit.domain.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

/**
 * 手动获取队列消息
 *
 * @author itaiit
 * @date 2022/9/4 12:21
 */
@Component
public class RabbitOrderReceiver {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageConverter messageConverter;

    public Order receiveOrder() {
//        Message receive = rabbitTemplate.receive("tacocloud.order");
//        return (Order) messageConverter.fromMessage(receive);
        return rabbitTemplate.receiveAndConvert("tacocloud.order",
                new ParameterizedTypeReference<Order>() {
                });
    }
}
