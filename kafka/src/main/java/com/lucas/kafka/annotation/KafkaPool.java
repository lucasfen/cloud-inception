package com.lucas.kafka.annotation;

import java.lang.annotation.*;

/**
 * @author Lucasfen
 * @Date 2021/05/26
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KafkaPool {

    /**
     * 自定义线程池的beanName
     */
    String value();

    /**
     * 是否使用池子
     */
    boolean poolEnable() default true;
}
