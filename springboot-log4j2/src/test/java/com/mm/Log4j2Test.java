package com.mm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = Log4j2Test.class)
public class Log4j2Test {

    @Test
    public void testLog() {
        log.debug("I am debug log.");
        log.info("I am info log.");
        log.warn("I am warn log.");
        log.error("I am error log.");
    }
}