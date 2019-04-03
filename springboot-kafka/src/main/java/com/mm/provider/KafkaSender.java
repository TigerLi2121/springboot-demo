package com.mm.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.beans.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class KafkaSender {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.mytopic.topic}")
    private String topic;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 发送消息
     */
    public void send(Long id) throws JsonProcessingException {
        Message message = new Message();
        message.setId(id);
        message.setMsg("Kafka消息:" + UUID.randomUUID());
        message.setSendTime(new Date());
        String sendJson = objectMapper.writeValueAsString(message);
        log.info("sender message:{}", sendJson);
        kafkaTemplate.send(topic, sendJson);
    }
}
