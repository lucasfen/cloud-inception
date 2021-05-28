package com.lucas.util.concurrent;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "schedule.thread.pool")
@Data
public class ScheduleThreadPoolConfig extends IotThreadPoolConfig {

    private String poolName = "scheduleThreadPool";
    //核心线程数
    private int corePoolSize = 5;

}
