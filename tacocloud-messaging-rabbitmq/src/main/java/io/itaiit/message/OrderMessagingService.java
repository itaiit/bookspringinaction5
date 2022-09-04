package io.itaiit.message;

import io.itaiit.domain.Order;

/**
 * @author itaiit
 * @date 2022/9/4 11:40
 */
public interface OrderMessagingService {

    void sendOrder(Order order);

}
