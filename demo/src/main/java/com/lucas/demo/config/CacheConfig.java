package com.lucas.demo.config;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucasfen
 * @Date 2021/06/19
 */
@Configuration
@Slf4j
public class CacheConfig {

    @Bean("emailTemplateCacheManager")
    public CacheManager deviceSupportedThirdPartyCacheManagerWithCaffeine() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine caffeine = Caffeine.newBuilder()
                // cache的初始容量值
                .initialCapacity(100)
                // cache的最大容量值
                .maximumSize(1000)
                // cache创建或修改后多久过期
                .expireAfterWrite(60, TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setAllowNullValues(false);
        log.info("Local cache init successfully !");
        return cacheManager;
    }
}
