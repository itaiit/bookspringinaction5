package io.itaiit.data;


import io.itaiit.domain.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
  List<Order> findByUsernameOrderByPlacedAtDesc(String username);

}
