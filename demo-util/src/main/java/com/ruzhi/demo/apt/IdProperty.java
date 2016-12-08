package com.ruzhi.demo.apt;

/**
 * Created by chunlong.wchl on 2016/1/16.
 * Description: 修饰标识属性
 */

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface IdProperty {
    String column();

    String type();

    String generator();
}


