package com.ruzhi.demo.test.all.one;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 将要被创建接口的方法
 * Created by sxf on 15-3-15.
 */
@Target(ElementType.METHOD)
public @interface MyAnnotation {
}
