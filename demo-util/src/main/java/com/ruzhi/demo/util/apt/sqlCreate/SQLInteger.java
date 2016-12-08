//: annotations/database/SQLInteger.java
package com.ruzhi.demo.util.apt.sqlCreate;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
    String name() default "";

    Constraints constraints() default @Constraints;
} ///:~
