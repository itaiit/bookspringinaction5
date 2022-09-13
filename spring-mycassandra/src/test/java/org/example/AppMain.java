package org.example;

import com.datastax.oss.driver.api.core.CqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author itaiit
 * @date 2022/9/13 1:10
 */
@SpringBootTest
public class AppMain {

    @Autowired
    private CqlSession cqlSession;

    @Test
    void test01() {

        System.out.println("cqlSession = " + cqlSession);


    }
}
