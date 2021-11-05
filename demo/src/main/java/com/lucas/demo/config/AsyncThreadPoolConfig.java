package com.lucas.demo.config;

import java.util.concurrent.ThreadPoolExecutor;

import com.lucas.util.concurrent.IotThreadPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucasfen
 * @Date 2021/09/01
 */
@Configuration
public class AsyncThreadPoolConfig {

    @Bean("testExecutor")
    public ThreadPoolExecutor testExecutor() {
        return new IotThreadPool(4, "test");
    }
}
