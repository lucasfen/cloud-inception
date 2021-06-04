package com.lucas.demo.controller.testcontroller;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/06/04
 */
@RestController
@RequestMapping("/test/kafka")
@Slf4j
public class TestKafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaSend;

    @PostMapping("/send")
    public void send(String info) {
        log.info(info);
        kafkaSend.send("mykafka", info);
    }
}
