package io.itaiit.data;

import io.itaiit.domain.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author itaiit
 * @date 2022/8/25 23:45
 */
public interface UserRepository extends ReactiveCassandraRepository<User, UUID> {

    @AllowFiltering
    Mono<User> findByUsername(String userName);
}
