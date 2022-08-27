package io.itaiit.data;

import io.itaiit.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author itaiit
 * @date 2022/8/25 23:45
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String userName);
}
