package com.mm.com.mm.hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shmily
 * @date 2019/4/1
 */
@Component
public class HelloSender {

//    public static final String EXCHANGE = "ximalayaos_hf";

    public static final String SONG_LIST = "hf_song_list";

    public static final String LOGIN = "hf_login";


    public static final String EXCHANGE = "ximalayaos_ps";

    public static final String PICTURE_LIST = "ps_picture_list";

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
//        String context = "hello " + new Date();
//        System.out.println("Sender : " + context);
//        this.rabbitTemplate.convertAndSend("hello", context);
        Map<String, String> content = new HashMap<>();
        content.put("type", PICTURE_LIST);
        rabbitTemplate.convertAndSend(EXCHANGE, "server." + "e44790cd51ef", om.writeValueAsString(content));
    }
}
