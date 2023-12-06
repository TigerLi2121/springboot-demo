package com.mm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mm.com.mm.hello.HelloSender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author tigerli
 * @date 2019/4/1
 */
@SpringBootTest
public class HelloTest {

    @Resource
    private HelloSender helloSender;

    @Test
    public void send() throws JsonProcessingException {
        helloSender.send();
    }
}
