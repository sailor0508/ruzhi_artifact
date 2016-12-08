package com.ruzhi.demo.apt;

/**
 * Created by chunlong.wchl on 2016/1/16.
 * Description: 修饰表属性
 */

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Persistent {
    String table();
}