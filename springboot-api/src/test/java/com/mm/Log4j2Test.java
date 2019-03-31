package com.mm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class Log4j2Test {

    @Test
    public void testLog(){
        log.trace("I am trace log.");
        log.debug("I am debug log.");
        log.info("I am info log.");
        log.warn("I am warn log.");
        log.error("I am error log.");
    }
}