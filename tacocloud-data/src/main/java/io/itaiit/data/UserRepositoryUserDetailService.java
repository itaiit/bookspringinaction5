package io.itaiit.data;

import io.itaiit.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author itaiit
 * @date 2022/8/25 23:50
 */
@Slf4j
@Profile("webservlet")
@Service
public class UserRepositoryUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUserName = userRepository.findByUsername(username);
        log.info("byUserName: " + byUserName.toString());
        return byUserName;
    }
}
