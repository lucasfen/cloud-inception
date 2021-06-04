package com.lucas.demo.controller.testcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Lucasfen
 * @Date 2021/05/26
 */
@Component
@Slf4j
public class TestKafkaConsumer {

    @KafkaListener(topics = "mykafka")
    public void receiver(String msg) {
        log.info("receive:" + msg);
    }
}
