package com.mm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class LogTest {

    ExecutorService es = new ThreadPoolExecutor(10, 10, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "log-thread"));

    @Test
    public void print() {
        for (int i = 0; i < 100000000; i++) {
            final int a = i;
            es.submit(() -> {
                log.trace("trace " + a);
                log.debug("debug " + a);
                log.warn("warn " + a);
                log.error("error " + a);
            });
        }
    }
}
