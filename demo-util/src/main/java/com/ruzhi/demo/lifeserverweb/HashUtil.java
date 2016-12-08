package com.ruzhi.demo.lifeserverweb;

/**
 * 字符串HASH工具
 * */
public class HashUtil {

    /**
     * 混合hash算法，输出64位的值
     */
    public static long mixHash(String str) {
        if(StringUtil.isEmpty(str)){
            return 0;
        }
        long hash = str.hashCode();
        hash <<= 32;
        hash |= FNVHash1(str);
        hash = Math.abs(hash);
        return hash;
    }

    /**
     * 改进的32位FNV算法1
     *
     * @param data
     *            字符串
     * @return int值
     */
    public static int FNVHash1(String data) {
        if(StringUtil.isEmpty(data)){
            return 0;
        }
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++)
            hash = (hash ^ data.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }
}
