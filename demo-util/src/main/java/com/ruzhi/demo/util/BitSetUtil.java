package com.ruzhi.demo.util;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by zhenggangji on 15/3/22.
 */
public class BitSetUtil {
    /**
     * 取得所有index
     * @param bitSet
     * @return
     */
    public static List<Integer> getAllIndex(BitSet bitSet){
        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = bitSet.nextSetBit(0); i != -1; i = bitSet.nextSetBit(i + 1)) {
            indexes.add(i);
        }
        return indexes;
    }
}
