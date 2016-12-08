//: annotations/database/Uniqueness.java
// Sample of nested annotations
package com.ruzhi.demo.util.apt.sqlCreate;

public @interface Uniqueness {
    Constraints constraints() default @Constraints(unique = true);
} ///:~
