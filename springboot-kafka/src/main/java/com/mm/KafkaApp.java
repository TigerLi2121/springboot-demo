package com.mm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mm.provider.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author shmily
 */
@SpringBootApplication
public class KafkaApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaApp.class, args);
        KafkaSender kafkaSender = context.getBean(KafkaSender.class);

        for (int i = 0; i < 3; i++) {
            try {
                kafkaSender.send(Long.valueOf(i));
                Thread.sleep(3000);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
