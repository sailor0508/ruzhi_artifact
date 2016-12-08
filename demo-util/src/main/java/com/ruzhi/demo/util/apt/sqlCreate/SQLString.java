//: annotations/database/SQLString.java
package com.ruzhi.demo.util.apt.sqlCreate;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    int value() default 0;

    String name() default "";

    Constraints constraints() default @Constraints;
} ///:~
