/*
 * projectName: cloud-common
 * date: 2019-11-15
 * copyright(c) 2017-2020 etekcity.com.cn
 */
package com.lucas.util.concurrent;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tadonis
 * @since 1.4.23
 */
@Configuration
@ConfigurationProperties(prefix = "iot.thread.pool")
@Data
public class IotThreadPoolConfig {
    protected String poolName = "iotThreadPool";

    //核心线程数
    protected int corePoolSize = 5;
}
