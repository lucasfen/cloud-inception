package com.lucas.annotation.Log;

import java.lang.annotation.*;

/**
 * @author Lucasfen
 * @Date 2021/06/07
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Log {
    String name() default "start";

}
