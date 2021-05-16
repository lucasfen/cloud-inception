package com.lucas.redis;

import java.nio.charset.Charset;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author Levi
 * @date 2020/3/12/012
 */
public class Jackson3RedisSerializer<T> implements RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Jackson2JsonRedisSerializer<T> jackson2JsonRedisSerializer;

    public Jackson3RedisSerializer(Class<T> clazz) {
        super();
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        this.jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(clazz);
        this.jackson2JsonRedisSerializer.setObjectMapper(om);
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return jackson2JsonRedisSerializer.serialize(t);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        try {
            return jackson2JsonRedisSerializer.deserialize(bytes);
        } catch (Exception e) {
            String str = new String(bytes, DEFAULT_CHARSET);
            return (T) str;
        }
    }

}
