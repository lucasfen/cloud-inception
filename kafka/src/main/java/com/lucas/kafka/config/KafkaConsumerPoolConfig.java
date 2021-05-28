package com.lucas.kafka.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucasfen
 * @Date 2021/05/26
 */
@Configuration
@EnableConfigurationProperties(KafkaConsumerPoolProperties.class)
public class KafkaConsumerPoolConfig {
}
