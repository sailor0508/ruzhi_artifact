package com.ruzhi.demo.test.all;

import java.lang.annotation.*;

/**
 * 定义列的类型
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    int value() default 64;
    String name() default "";
    Constraints constraints() default @Constraints;
}
