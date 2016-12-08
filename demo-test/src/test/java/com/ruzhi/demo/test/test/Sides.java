package com.ruzhi.demo.test.test;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by chunlong.wchl on 2015/5/30.
 * http://www.javalobby.org/java/forums/t17491.html   例子和解释（当单例遇上序列化。。。。）
 * http://rmn190.iteye.com/blog/350193
 *
 */
public final class Sides implements Serializable {
    private int value;
    private Sides(int newVal) { value = newVal; }
    private static final int LEFT_VALUE = 1;
    private static final int RIGHT_VALUE = 2;
    private static final int TOP_VALUE = 3;
    private static final int BOTTOM_VALUE = 4;

    public static final Sides LEFT = new Sides(LEFT_VALUE);
    public static final Sides RIGHT = new Sides(RIGHT_VALUE);
    public static final Sides TOP = new Sides(TOP_VALUE);
    public static final Sides BOTTOM = new Sides(BOTTOM_VALUE);

    /**
     * JVM从内存中反序列化地"组装"一个新对象时,就会自动调用这个 readResolve方法
     */
    private Object readResolve() throws ObjectStreamException {
        // Switch on this instance's value to figure out which class variable
        // this is meant to match
        switch(value) {
            case LEFT_VALUE: return LEFT;
            case RIGHT_VALUE: return RIGHT;
            case TOP_VALUE: return TOP;
            case BOTTOM_VALUE: return BOTTOM;
        }
        return null;
    }
}