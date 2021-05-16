package com.lucas.common.config;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

/**
 * @author: lucasfen
 * @create: 2021/02/07
 */
public class LocalCacheConfig {

    @Bean
    public CacheManager cacheManagerWithCafeine() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine caffeine = Caffeine.newBuilder()
                // cache的初始容量值
                .initialCapacity(100)
                // cache的最大容量值
                .maximumSize(1000)
                // cache创建或修改后多久过期
                .expireAfterWrite(10, TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setAllowNullValues(false);
        return cacheManager;
    }
}
