//: annotations/ExtractInterface.java
// APT-based annotation processing.
package com.ruzhi.demo.util.apt.thinkInJava;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ExtractInterface {
  public String value();
} ///:~
