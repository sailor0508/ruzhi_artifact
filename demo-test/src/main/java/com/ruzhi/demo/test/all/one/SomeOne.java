package com.ruzhi.demo.test.all.one;

/**
 * 接口生成实例
 * Created by sxf on 15-3-14.
 */
public class SomeOne {
    int k = 0;

    public SomeOne(int k) {
        this.k = k;
    }

    @MyAnnotation
    public int getK() {
        //Main main = context.b;
        return k;
    }

    @MyAnnotation
    public void printK() {
        printK();
    }

    @MyAnnotation
    public int HaveTest(int a) {
        return a + k;
    }


}
