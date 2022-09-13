package io.itaiit.data;


import io.itaiit.domain.Order;
import io.itaiit.domain.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface OrderRepository extends ReactiveCrudRepository<Order, UUID> {
    @AllowFiltering
    Flux<Order> findByUserOrderByPlacedAtDesc(
            User user);
}
