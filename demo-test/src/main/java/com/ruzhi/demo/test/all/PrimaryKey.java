package com.ruzhi.demo.test.all;

import java.lang.annotation.*;
/**
 * PrimaryKey
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {
    Constraints constraints() default @Constraints(primarykey = true);
}

