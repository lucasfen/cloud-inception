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
@ConfigurationProperties(prefix = "async.thread.pool")
@Data
public class IotAsyncThreadPoolConfig extends IotThreadPoolConfig{
    private String poolName = "asyncThreadPool";

    //核心线程数
    private int corePoolSize = 5;
}
