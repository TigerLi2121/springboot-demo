package com.mm.com.mm.hello;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author shmily
 * @date 2019/4/1
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
