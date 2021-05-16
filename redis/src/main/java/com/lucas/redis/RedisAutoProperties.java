package com.lucas.redis;

import lombok.Data;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

/**
 * @Author: Jimmy
 * @Date: 2020-03-01 00:39
 */
@Data
public class RedisAutoProperties extends RedisProperties {

    private String monitorName;

    public RedisAutoProperties(String monitorName) {

        this.monitorName = monitorName;
    }
}
