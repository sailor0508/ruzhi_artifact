package com.ruzhi.demo.test.self.validation;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
public interface Animal {
    @SelfNotEmpty String getName();
    @SelfNotEmpty String getOwnerName();
}

