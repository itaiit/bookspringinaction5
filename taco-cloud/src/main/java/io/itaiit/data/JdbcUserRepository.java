package io.itaiit.data;

import io.itaiit.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;

/**
 * @author itaiit
 * @date 2022/9/17 10:06
 */
@Slf4j
@Service
public class JdbcUserRepository extends JdbcDaoSupport implements UserRepository {

    private SimpleJdbcInsert simpleUserInsert;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        simpleUserInsert = new SimpleJdbcInsert(jdbcTemplate);
        setJdbcTemplate(jdbcTemplate);
    }

    @Override
    public User findByUsername(String userName) {
        PreparedStatementCreatorFactory statementCreatorFactory = new PreparedStatementCreatorFactory(
                "select * from user where username=?",
                Types.VARCHAR
        );
        PreparedStatementCreator statementCreator = statementCreatorFactory.newPreparedStatementCreator(Arrays.asList(userName));
        List<User> users = getJdbcTemplate().query(statementCreator, new BeanPropertyRowMapper<>(User.class));
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public Long save(User user) {


        return null;
    }
}
