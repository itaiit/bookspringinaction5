package io.itaiit.data;


import io.itaiit.domain.Order;

public interface OrderRepository {

  Order save(Order order);

}
