package com.ruzhi.demo.util;

public class BitUtils {

    /**
     * 2的几次方
     *
     * @param position
     * @return
     */
    public static int pow2(int num) {
        return ((Double) Math.pow(2, num)).intValue();
    }

    /**
     * 判断 一个数number的二进制格式的第position位是否为1
     *
     * @param number 十进制数
     * @param position 第几位 右到左从0开始
     * @return
     */
    public static boolean isTrue(long number, int position) {
        long decimalValue = pow2(position);
        return (decimalValue & number) == decimalValue;
    }

    /**
     * 位或 运算
     *
     * @param m
     * @param n
     * @return
     */
    public static int computeOR(int m, int n) {
        return m | n;
    }

    /**
     * 针对一个10进制数的位运算，增加位
     *
     * @param number 10进制数
     * @param position 位数 从0开始
     * @return
     */
    public static int addBit(int number, int position) {
        if (number < 0 || position < 0)
            return 0;
        return computeOR(number, pow2(position));
    }

    /**
     * 针对一个10进制数的位运算，删除位
     *
     * @param number 10进制数
     * @param position 位数 从0开始
     * @return
     */
    public static int removeBit(int number, int position) {
        if (number < 0 || position < 0)
            return 0;

        if (isTrue(number, position)) {
            number = number - pow2(position);
        }
        return number;

    }

    public static long computeW(long number, int position) {
        if (number < 0 || position < 0)
            return 0;

        return number >> position;

    }

    public static void main(String[] args) {
        System.out.println(pow2(7));
    }
}
