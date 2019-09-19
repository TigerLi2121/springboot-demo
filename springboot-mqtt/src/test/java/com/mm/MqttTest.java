package com.mm;

import com.mm.mqtt.IMqttSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试
 * @author shmily
 * @date 2019/5/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqttTest {

    @Autowired
    private IMqttSender sender;

    /**
     * 发送消息
     */
    @Test
    public void send(){
        sender.sendToMqtt("hello mqtt");
    }

    /**
     * 发送消息
     */
    @Test
    public void send2(){
        sender.sendToMqtt("topic2","hello mqtt2");
    }

    /**
     * 发送消息
     */
    @Test
    public void send3(){
        sender.sendToMqtt("topic3",1,"hello mqtt3");
    }
}
