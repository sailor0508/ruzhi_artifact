package com.ruzhi.demo.test.spring.aop;

import com.jcabi.aspects.Loggable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chunlong.wchl on 2015/6/2.
 */
public class Foo {
    private static Logger log = LoggerFactory.getLogger(Foo.class);
    public  static  void main(String args[]){
        new Foo().power(2, 3);
    }

    @Loggable
    public double power(int x, int p) {
        return Math.pow(x, p);
    }
}