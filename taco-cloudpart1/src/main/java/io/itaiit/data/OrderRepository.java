package io.itaiit.data;


import io.itaiit.domain.Order;
import io.itaiit.domain.User;

import java.util.List;

public interface OrderRepository {

  Order save(Order order);

  List<Order> findByUserOrderByPlaceAtDesc(User user);

}
