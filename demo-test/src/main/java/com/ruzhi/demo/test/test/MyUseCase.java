package com.ruzhi.demo.test.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个包含两个参数的简单测试用例注解，此注解仅适用于"方法".
 *
 * @author <a href="mailto:cn.java.river@gmail.com">Tao</a>
 * @since 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyUseCase {
    /**
     * 测试用例编号
     */
    public int id();

    /**
     * 测试用例描述
     */
    public String description() default "no description";
}
