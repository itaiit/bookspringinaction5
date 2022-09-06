package io.itaiit;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author itaiit
 * @date 2022/9/6 23:48
 */
public class ReactorTest {

    @Test
    void test01() {
        Mono.just("abc")
                .map(e -> {
                    System.out.println("map1 threadName:" + Thread.currentThread().getName());
                    return e.toUpperCase();
                })
                .subscribe();
    }

    @Test
    void test02() {
        Flux.fromArray(new String[]{"a", "b", "c"})
                .map(item -> {
                            return item.toUpperCase();
                        }
                )
                .subscribe(item -> {
                            System.out.println("item = " + item + ", thread-name: " + Thread.currentThread().getName());
                        }
                );
        System.out.println("main..................");
    }

    @Test
    void test03() {
        Flux.range(5, 4)
                .subscribe(e -> System.out.println("e = " + e));
    }

    @Test
    void test04() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(2))
                .take(5)
                .subscribe(e ->
                        {
                            System.out.println("e = " + e + ", thread-name: " + Thread.currentThread().getName() + ", " + Thread.currentThread().isDaemon());
                        }
                );
        TimeUnit.SECONDS.sleep(100);
    }
}
