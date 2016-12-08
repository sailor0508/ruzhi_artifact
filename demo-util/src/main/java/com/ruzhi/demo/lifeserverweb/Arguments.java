package com.ruzhi.demo.lifeserverweb;

/**
 * 参数校验
 *
 * @author longhang.sc
 * @date 20130409
 */
public class Arguments {

    /**
     * 不能为NULL
     *
     * @param obj
     * @param msg
     */
    public static void notNull(Object obj, String msg) {
        if (obj == null)
            throw new IllegalArgumentException(msg);
    }

    /**
     * 不能为空
     * @param str
     * @param msg
     */
    public static void notBlank(String str, String msg) {
        if (str == null || "".equals(str.trim()))
            throw new IllegalArgumentException(msg);
    }

    /**
     * 必须大于0
     * @param num
     * @param msg
     */
    public static void notZero(long num ,String msg) {
        if (num <= 0 )
            throw new IllegalArgumentException(msg);
    }

    /**
     *
     * @param flag
     * @param msg
     */
    public static void notFalse(boolean flag ,String msg) {
        if(flag) {
            throw new IllegalArgumentException(msg);
        }
    }



}
