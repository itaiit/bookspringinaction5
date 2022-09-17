package io.itaiit.data;

import io.itaiit.domain.User;

/**
 * @author itaiit
 * @date 2022/8/25 23:45
 */
public interface UserRepository {
    User findByUsername(String userName);

    Long save(User user);

}
