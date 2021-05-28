package com.lucas.redis;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Cluster;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Sentinel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: lucas
 * @Date: 2020-02-29 23:00
 */
@Configuration
public class RedisAutoConfig {

//    @Bean(name = "vesyncRedisProperties")
//    @ConfigurationProperties(prefix = "vesync.redis")
//    @ConditionalOnProperty(prefix = "vesync.redis", name = "enable", havingValue = "true")
//    public RedisAutoProperties vesyncRedisProperties() {
//        return new RedisAutoProperties("vesync-redis-pool");
//    }
//
//    @Bean(name = "statRedisProperties")
//    @ConfigurationProperties(prefix = "stat.redis")
//    @ConditionalOnProperty(prefix = "stat.redis", name = "enable", havingValue = "true")
//    public RedisAutoProperties statRedisProperties() {
//        return new RedisAutoProperties("stat-redis-pool");
//    }
//
//    @Bean(name = "redis7aRedisProperties")
//    @ConfigurationProperties(prefix = "redis7a.redis")
//    @ConditionalOnProperty(prefix = "redis7a.redis", name = "enable", havingValue = "true")
//    public RedisAutoProperties redis7aRedisProperties() {
//        return new RedisAutoProperties("redis7a-redis-pool");
//    }
//
//    @Bean(name = "vdmpRedisProperties")
//    @ConditionalOnProperty(prefix = "vdmp.redis", name = "enable", havingValue = "true")
//    @ConfigurationProperties(prefix = "vdmp.redis")
//    public RedisAutoProperties vdmpRedisProperties() {
//        return new RedisAutoProperties("vdmp-redis-pool");
//    }
//
//    @Bean(name = "assureaptRedisProperties")
//    @ConditionalOnProperty(prefix = "assureapt.redis", name = "enable", havingValue = "true")
//    @ConfigurationProperties(prefix = "assureapt.redis")
//    public RedisAutoProperties assureaptRedisProperties() {
//        return new RedisAutoProperties("assureapt-redis-pool");
//    }
//
//    @Bean(name = "deviceRedisProperties")
//    @ConditionalOnProperty(prefix = "device.redis", name = "enable", havingValue = "true")
//    @ConfigurationProperties(prefix = "device.redis")
//    public RedisAutoProperties devRedisProperties() {
//        return new RedisAutoProperties("device-redis-pool");
//    }
//
//    @Bean(name = "userRedisProperties")
//    @ConditionalOnProperty(prefix = "user.redis", name = "enable", havingValue = "true")
//    @ConfigurationProperties(prefix = "user.redis")
//    public RedisAutoProperties userRedisProperties() {
//        return new RedisAutoProperties("user-redis-pool");
//    }
//
//    @Bean("vesyncConnectionFactory")
//    @ConditionalOnBean(name = "vesyncRedisProperties")
//    public RedisConnectionFactory vesyncConnectionFactory(@Qualifier("vesyncRedisProperties") RedisAutoProperties redisProperties) {
//        return createRedisConnectionFactory(redisProperties);
//    }
//
//    @Bean("statConnectionFactory")
//    @ConditionalOnBean(name = "statRedisProperties")
//    public RedisConnectionFactory statConnectionFactory(@Qualifier("statRedisProperties") RedisAutoProperties redisProperties) {
//        return createRedisConnectionFactory(redisProperties);
//    }
//
//    @Bean("redis7aConnectionFactory")
//    @ConditionalOnBean(name = "redis7aRedisProperties")
//    public RedisConnectionFactory redis7aConnectionFactory(@Qualifier("redis7aRedisProperties") RedisAutoProperties redisProperties) {
//        return createRedisConnectionFactory(redisProperties);
//    }
//
//    @Bean("vdmpConnectionFactory")
//    @ConditionalOnBean(name = "vdmpRedisProperties")
//    public RedisConnectionFactory vdmpConnectionFactory(@Qualifier("vdmpRedisProperties") RedisAutoProperties redisProperties) {
//        return createRedisConnectionFactory(redisProperties);
//
//    }
//
//    @Bean("assureaptConnectionFactory")
//    @ConditionalOnBean(name = "assureaptRedisProperties")
//    public RedisConnectionFactory assureaptConnectionFactory(@Qualifier("assureaptRedisProperties") RedisAutoProperties redisProperties) {
//        return createRedisConnectionFactory(redisProperties);
//
//    }
//
//    @Bean("deviceConnectionFactory")
//    @ConditionalOnBean(name = "deviceRedisProperties")
//    public RedisConnectionFactory deviceConnectionFactory(@Qualifier("deviceRedisProperties") RedisAutoProperties redisProperties) {
//        return createRedisConnectionFactory(redisProperties);
//    }
//
//    @Bean("userConnectionFactory")
//    @ConditionalOnBean(name = "userRedisProperties")
//    public RedisConnectionFactory userConnectionFactory(@Qualifier("userRedisProperties") RedisAutoProperties redisProperties) {
//        return createRedisConnectionFactory(redisProperties);
//    }
//
//    @Bean("vesyncStringRedisTemplate")
//    @ConditionalOnBean(name = "vesyncConnectionFactory")
//    public StringRedisTemplate vesyncStringRedisTemplate(@Qualifier("vesyncConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createStringRedisTemplate(redisConnectionFactory);
//
//    }
//
//    @Bean("statStringRedisTemplate")
//    @ConditionalOnBean(name = "statConnectionFactory")
//    public StringRedisTemplate statStringRedisTemplate(@Qualifier("statConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createStringRedisTemplate(redisConnectionFactory);
//
//    }
//
//    @Bean("redis7aStringTemplate")
//    @ConditionalOnBean(name = "redis7aConnectionFactory")
//    public StringRedisTemplate redis7aStringTemplate(@Qualifier("redis7aConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createStringRedisTemplate(redisConnectionFactory);
//    }
//
//    @Bean("vdmpStringRedisTemplate")
//    @ConditionalOnBean(name = "vdmpConnectionFactory")
//    public StringRedisTemplate vdmpStringRedisTemplate(@Qualifier("vdmpConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createStringRedisTemplate(redisConnectionFactory);
//
//    }
//
//    @Bean("assureaptStringRedisTemplate")
//    @ConditionalOnBean(name = "assureaptConnectionFactory")
//    public StringRedisTemplate assureaptStringRedisTemplate(@Qualifier("assureaptConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createStringRedisTemplate(redisConnectionFactory);
//
//    }
//
//    @Bean("deviceStringRedisTemplate")
//    @ConditionalOnBean(name = "deviceConnectionFactory")
//    public StringRedisTemplate deviceStringRedisTemplate(@Qualifier("deviceConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createStringRedisTemplate(redisConnectionFactory);
//
//    }
//
//    @Bean("userStringRedisTemplate")
//    @ConditionalOnBean(name = "userConnectionFactory")
//    public StringRedisTemplate userStringRedisTemplate(@Qualifier("userConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createStringRedisTemplate(redisConnectionFactory);
//
//    }
//
//    @Bean("vesyncRedisTemplate")
//    @ConditionalOnBean(name = "vesyncConnectionFactory")
//    public RedisTemplate<Object, Object> vesyncRedisTemplate(
//            @Qualifier("vesyncConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createRedisTemplate(redisConnectionFactory);
//    }
//
//    @Bean("statRedisTemplate")
//    @ConditionalOnBean(name = "statConnectionFactory")
//    public RedisTemplate<Object, Object> statRedisTemplate(
//            @Qualifier("statConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createRedisTemplate(redisConnectionFactory);
//
//    }
//
//    @Bean("redis7aTemplate")
//    @ConditionalOnBean(name = "redis7aConnectionFactory")
//    public RedisTemplate<Object, Object> redis7aTemplate(@Qualifier("redis7aConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createRedisTemplate(redisConnectionFactory);
//
//    }
//
//    @Bean("vdmpRedisTemplate")
//    @ConditionalOnBean(name = "vdmpConnectionFactory")
//    public RedisTemplate<Object, Object> vdmpRedisTemplate(@Qualifier("vdmpConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createRedisTemplate(redisConnectionFactory);
//
//    }
//    @Bean("assureaptRedisTemplate")
//    @ConditionalOnBean(name = "assureaptConnectionFactory")
//    public RedisTemplate<Object, Object> assureaptRedisTemplate(@Qualifier("assureaptConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createRedisTemplate(redisConnectionFactory);
//
//    }
//
//    @Bean("deviceRedisTemplate")
//    @ConditionalOnBean(name = "deviceConnectionFactory")
//    public RedisTemplate<Object, Object> deviceRedisTemplate(
//            @Qualifier("deviceConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createRedisTemplate(redisConnectionFactory);
//    }
//
//    @Bean("userRedisTemplate")
//    @ConditionalOnBean(name = "userConnectionFactory")
//    public RedisTemplate<Object, Object> userRedisTemplate(
//            @Qualifier("userConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        return createRedisTemplate(redisConnectionFactory);
//    }


    @Primary
    @Bean(name="lucasRedisProperties")
    @ConfigurationProperties("lucas.redis")
    @ConditionalOnProperty(prefix = "lucas.redis", name = "enable", havingValue = "true")
    public RedisAutoProperties lucasRedisProperties() {
        return new RedisAutoProperties("lucas-redis");
    }

    @Bean(name="lucasRedisConnectionFactory")
    @ConditionalOnBean(name = "lucasRedisProperties")
    public RedisConnectionFactory lucasRedisConnectionFactory(@Qualifier("lucasRedisProperties") RedisAutoProperties redisAutoProperties) {
        return createRedisConnectionFactory(redisAutoProperties);
    }

    @Bean(name="lucasStringRedisTemplate")
    @ConditionalOnBean(name="lucasRedisConnectionFactory")
    public StringRedisTemplate lucasStringRedisTemplate(@Qualifier("lucasRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return createStringRedisTemplate(redisConnectionFactory);
    }

    @Bean(name="lucasRedisTemplate")
    @ConditionalOnBean(name="lucasRedisConnectionFactory")
    public RedisTemplate<Object, Object> lucasRedisTemplate(@Qualifier("lucasRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return createRedisTemplate(redisConnectionFactory);
    }

    private RedisConnectionFactory createRedisConnectionFactory(RedisAutoProperties redisProperties) {
        RedisConnectionFactory redisConnectionFactory;
        if (redisProperties.getJedis().getPool() != null) {
            redisConnectionFactory = createJedisConnectionFactory(redisProperties);
        } else if (redisProperties.getLettuce().getPool() != null) {
            redisConnectionFactory = createLettuceConnectionFactory(redisProperties);
        } else {
            throw new RuntimeException("redis 配置未使用连接池池子,请检查配置");
        }
        //MetricManager.registerCommonPoolMetric(redisProperties.getMonitorName(), redisProperties.getMonitorName());
        return redisConnectionFactory;
    }

    private StringRedisTemplate createStringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }

    private RedisTemplate<Object, Object> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Jackson3RedisSerializer<Object> serializer = new Jackson3RedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(serializer);
        return redisTemplate;
    }

    private JedisConnectionFactory createJedisConnectionFactory(RedisAutoProperties redisProperties) {
        JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        if (redisProperties.isSsl()) {
            builder.useSsl();
        }
        if (redisProperties.getTimeout() != null) {
            Duration timeout = redisProperties.getTimeout();
            builder.readTimeout(timeout).connectTimeout(timeout);
        }
        builder.usePooling().poolConfig(jedisPoolConfig(redisProperties));
        if (StringUtils.hasText(redisProperties.getUrl())) {
            ConnectionInfo connectionInfo = parseUrl(redisProperties.getUrl());
            if (connectionInfo.isUseSsl()) {
                builder.useSsl();
            }
        }
        JedisClientConfiguration clientConfiguration = builder.build();
        JedisConnectionFactory factory;
        RedisSentinelConfiguration sentinelConfig = getSentinelConfig(redisProperties);
        RedisClusterConfiguration clusterConfig = getClusterConfiguration(redisProperties);
        if (sentinelConfig != null) {
            factory = new JedisConnectionFactory(sentinelConfig, clientConfiguration);
        } else if (clusterConfig != null) {
            factory = new JedisConnectionFactory(clusterConfig,
                    clientConfiguration);
        } else {
            factory = new JedisConnectionFactory(getStandaloneConfig(redisProperties), clientConfiguration);

        }
        return factory;
    }

    private LettuceConnectionFactory createLettuceConnectionFactory(RedisAutoProperties redisProperties) {

        // 1.获取LettuceClientConfiguration
        Pool pool = redisProperties.getLettuce().getPool();
        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder()
                .poolConfig(getPoolConfig(pool, redisProperties.getMonitorName()));
        if (redisProperties.isSsl()) {
            builder.useSsl();
        }
        if (StringUtils.hasText(redisProperties.getUrl())) {
            ConnectionInfo connectionInfo = parseUrl(redisProperties.getUrl());
            if (connectionInfo.isUseSsl()) {
                builder.useSsl();
            }
        }
        if (redisProperties.getTimeout() != null) {
            builder.commandTimeout(redisProperties.getTimeout());
        }
        Lettuce lettuce = redisProperties.getLettuce();
        if (lettuce.getShutdownTimeout() != null
                && !lettuce.getShutdownTimeout().isZero()) {
            builder.shutdownTimeout(redisProperties.getLettuce().getShutdownTimeout());
        }
        LettuceClientConfiguration lettuceConfig = builder.build();

        // 2.创建LettuceConnectionFactory
        LettuceConnectionFactory factory;
        RedisSentinelConfiguration sentinelConfig = getSentinelConfig(redisProperties);
        RedisClusterConfiguration clusterConfig = getClusterConfiguration(redisProperties);
        if (sentinelConfig != null) {
            factory = new LettuceConnectionFactory(sentinelConfig, lettuceConfig);
        } else if (clusterConfig != null) {
            factory = new LettuceConnectionFactory(clusterConfig, lettuceConfig);
        } else {
            factory = new LettuceConnectionFactory(getStandaloneConfig(redisProperties), lettuceConfig);
        }
        factory.setShareNativeConnection(false);
        return factory;
    }

    private RedisSentinelConfiguration getSentinelConfig(RedisAutoProperties redisProperties) {
        Sentinel sentinelProperties = redisProperties.getSentinel();
        if (sentinelProperties == null) {
            return null;
        }
        RedisSentinelConfiguration config = new RedisSentinelConfiguration();
        config.master(sentinelProperties.getMaster());
        config.setSentinels(createSentinels(sentinelProperties));
        if (redisProperties.getPassword() != null) {
            config.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        config.setDatabase(redisProperties.getDatabase());
        return config;
    }

    private RedisClusterConfiguration getClusterConfiguration(RedisAutoProperties redisProperties) {
        Cluster clusterProperties = redisProperties.getCluster();
        if (clusterProperties == null) {
            return null;
        }
        RedisClusterConfiguration config = new RedisClusterConfiguration(clusterProperties.getNodes());

        if (clusterProperties.getMaxRedirects() != null) {
            config.setMaxRedirects(clusterProperties.getMaxRedirects());
        }

        if (redisProperties.getPassword() != null) {
            config.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        return config;

    }

    private JedisPoolConfig jedisPoolConfig(RedisAutoProperties redisProperties) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setJmxNamePrefix(redisProperties.getMonitorName());
        config.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        config.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        config.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        if (redisProperties.getJedis().getPool().getMaxWait() != null) {
            config.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
        }
        return config;
    }


    private RedisStandaloneConfiguration getStandaloneConfig(RedisAutoProperties redisProperties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        if (StringUtils.hasText(redisProperties.getUrl())) {
            ConnectionInfo connectionInfo = parseUrl(redisProperties.getUrl());
            config.setHostName(connectionInfo.getHostName());
            config.setPort(connectionInfo.getPort());
            config.setPassword(RedisPassword.of(connectionInfo.getPassword()));
        } else {
            config.setHostName(redisProperties.getHost());
            config.setPort(redisProperties.getPort());
            config.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        config.setDatabase(redisProperties.getDatabase());
        return config;
    }

    private GenericObjectPoolConfig getPoolConfig(Pool properties, String monitorName) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(properties.getMaxActive());
        config.setMaxIdle(properties.getMaxIdle());
        config.setMinIdle(properties.getMinIdle());
        config.setJmxNamePrefix(monitorName);
        if (properties.getMaxWait() != null) {
            config.setMaxWaitMillis(properties.getMaxWait().toMillis());
        }
        return config;
    }

    private List<RedisNode> createSentinels(Sentinel sentinel) {
        List<RedisNode> nodes = new ArrayList<>();
        for (String node : sentinel.getNodes()) {
            try {
                String[] parts = StringUtils.split(node, ":");
                Assert.state(parts.length == 2, "Must be defined as 'host:port'");
                nodes.add(new RedisNode(parts[0], Integer.valueOf(parts[1])));
            } catch (RuntimeException ex) {
                throw new IllegalStateException("Invalid redis sentinel " + "property '" + node + "'", ex);
            }
        }
        return nodes;

    }

    private ConnectionInfo parseUrl(String url) {
        try {
            URI uri = new URI(url);
            boolean useSsl = (url.startsWith("rediss://"));
            String password = null;
            if (uri.getUserInfo() != null) {
                password = uri.getUserInfo();
                int index = password.indexOf(':');
                if (index >= 0) {
                    password = password.substring(index + 1);
                }
            }
            return new ConnectionInfo(uri, useSsl, password);
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Malformed url '" + url + "'", ex);
        }
    }

    protected static class ConnectionInfo {

        private final URI uri;

        private final boolean useSsl;

        private final String password;

        ConnectionInfo(URI uri, boolean useSsl, String password) {
            this.uri = uri;
            this.useSsl = useSsl;
            this.password = password;
        }

        boolean isUseSsl() {
            return this.useSsl;
        }

        String getHostName() {
            return this.uri.getHost();
        }

        int getPort() {
            return this.uri.getPort();
        }

        String getPassword() {
            return this.password;
        }

    }

}
