package com.ruzhi.demo.util;

/**
 * Created by chunlong.wchl on 2016/1/31.
 * Description:
 */
public class TestClass {

    private int m;

    public int inc() {
        return m + 1;
    }
    public static void main(String agrs[]){
        new TestClass().inc();
    }
}
