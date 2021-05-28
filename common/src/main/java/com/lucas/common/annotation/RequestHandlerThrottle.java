package com.lucas.common.annotation;

import java.lang.annotation.*;

/**
 * @author Lucasfen
 * @Date 2021/05/27
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface RequestHandlerThrottle {

    int maxConcurrentLevel() default 1000;
    int maxRate() default 10000;
    long acquireTimeoutInMs() default 50;
}
