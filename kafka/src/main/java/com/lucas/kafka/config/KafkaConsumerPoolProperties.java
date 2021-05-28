package com.lucas.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Lucasfen
 * @Date 2021/05/26
 */
@ConfigurationProperties(prefix = "kafka.consumer.pool")
@Data
public class KafkaConsumerPoolProperties {

    private String poolName = "kafkaThreadPool";
    private int corePoolSize = 8;
    private int maxPoolSize = 16;
    private long keepAliveTimeMillSec = 1000;
}
