package com.ruzhi.demo.lifeserverweb;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串工具类，包括数字类型和字符串转换,日期时间格式转换
 */
public class NumberUtil {
    /**
     * 将字符串转换为 double类型
     */
    public static double getDouble(String s) {
        if (s == null){
            return 0;
        }
        double i = 0;
        try {
            i = Double.parseDouble(s);
        } catch (NumberFormatException e1) {
        }

        return i;
    }
    /**
     * 将字符串转换为long类型
     */
    public static long getLong(String s){
        if (s == null){
            return 0;
        }
        long i = 0;
        try {
            i = Long.parseLong(s);
        } catch (NumberFormatException e1) {
        }

        return i;
    }

    /**
     * 将UNIX毫秒数转换为字符串类型
     */
    public static String getDateStrFromUNIX(long unixTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date datelimit = new Date(unixTime);
        return sdf.format(datelimit);
    }

    /**
     * 将UNIX毫秒数转换成Date类型
     */
    public static Date getDateFromUNIX(long time){
        return  new Date(time);
    }


    public static void main(String args[]){
        getDouble(null);
    }
}
