package io.itaiit.message;

import io.itaiit.domain.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自动监听消息队列
 *
 * @author itaiit
 * @date 2022/9/4 12:31
 */
//@Component
public class OrderListener {

    @Autowired
    private KitchenUI ui;

    @RabbitListener(queues = "tacocloud.order")
    public void receiveOrder(Order order) {
        ui.displayOrder(order);
    }
}
