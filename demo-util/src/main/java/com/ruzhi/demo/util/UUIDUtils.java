package com.ruzhi.demo.util;

import java.util.UUID;

public class UUIDUtils {

    /* 随机种子 */
    private static char x36s[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    /* 随机数长度 */
    private static short len = 20;

    public static String newId() {
        char chs[] = new char[len];

        /**
         * 生成前8位随机字符(以系统时间为随机池, 以36位数字+英文字母为随机种子)
         */
        long v = (System.currentTimeMillis() - 936748800000L) >> 1;    //1999-9-9 开始，起码要横跨102年:)
        for (int i = 7; i > 0; i--) {
            chs[i] = x36s[(int) (v % 36)];
            v = v / 36;
        }
        chs[0] = x36s[(int) (v % 26) + 10]; //确保第一个随机字符是"字母", 以符合一般编程的标识符定义

        /**
         * 生成后12位随机字符(以UUID为随机池, 以36位数字+英文字母为随机种子)
         */
        UUID u = UUID.randomUUID();
        v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
        if (v < 0) {
            v = -v;
        }

        for (int i = 8; i < len; i++) {
            chs[i] = x36s[(int) (v % 36)];
            v = v / 36;
        }

        return new String(chs);
    }

    public static void main(String[] args) {
        System.out.println(newId());
    }
}
