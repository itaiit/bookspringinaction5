// tag::core[]
package io.itaiit.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.itaiit.domain.Order;
import io.itaiit.domain.Taco;
import io.itaiit.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order_Tacos");

        this.objectMapper = new ObjectMapper();

        this.jdbcTemplate = jdbc;

    }
// end::core[]

    // tag::save[]
    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            saveTacoToOrder(taco, orderId);
        }

        return order;
    }

    @Override
    public List<Order> findByUserOrderByPlaceAtDesc(User user) {
        String username = user.getUsername();
        List<Order> orders = jdbcTemplate.query(
                new PreparedStatementCreatorFactory("select * from Taco_Order where username=?", Types.VARCHAR)
                        .newPreparedStatementCreator(new Object[]{username}),
                new BeanPropertyRowMapper<>(Order.class)
        );
        return orders;
    }

    private long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values =
                objectMapper.convertValue(order, Map.class);
        log.info("saveOrderDetails values: " + values); // saveOrderDetails values: {id=null, placedAt=1661339812593, tacos=[{id=8, createdAt=1661339781632, name=myfavorite003, ingredients=[FLTO, CARN, CHED, LETC, SRCR]}], name=myfavorite003, street=green street, city=newyork, state=01, zip=5155, ccNumber=4725, ccExpiration=444, ccCVV=突然的}
        values.put("placedAt", order.getPlacedAt()); // objectMapper会将Date转成long的格式，需要手动设置一下
        User user = order.getUser();
        values.put("username", user.getUsername());

        long orderId =
                orderInserter
                        .executeAndReturnKey(values)
                        .longValue();
        return orderId;
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }
// end::save[]

/*
// tag::core[]

...

// end::core[]
 */

// tag::core[]
}
// end::core[]
