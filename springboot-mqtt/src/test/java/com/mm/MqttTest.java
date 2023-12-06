package com.mm;

import com.mm.mqtt.IMqttSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试
 *
 * @author tigerli
 * @date 2019/5/20
 */
@SpringBootTest
public class MqttTest {

    @Autowired
    private IMqttSender sender;

    /**
     * 发送消息
     */
    @Test
    public void send() {
        sender.sendToMqtt("hello mqtt");
    }

    /**
     * 发送消息
     */
    @Test
    public void send2() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
            sender.sendToMqtt("topic2/" + i + "/client", "hello mqtt" + i);
        }
    }

    /**
     * 发送消息
     */
    @Test
    public void send3() {
        sender.sendToMqtt("topic3", 1, "hello mqtt3");
    }
}
