/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ruzhi.demo.core.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

import java.awt.*;

/**
 * @author chunlong.wchl
 * @version $Id: CollectionsMain.java, v 0.1 2016-11-28 下午9:46 chunlong.wchl Exp $$
 */
public class CollectionsMain {

    public static void main(String args[]) {

    }

    /**
     * 怎么使用不可变集合
     * 不可变集合可以用如下多种方式创建：
     *
     * copyOf方法，如ImmutableSet.copyOf(set);
     * of方法，如ImmutableSet.of(“a”, “b”, “c”)或 ImmutableMap.of(“a”, 1, “b”, 2);
     * Builder工具:
     */
    ImmutableSet<Color> WEBSAFE_COLORS = null;
    public static final ImmutableSet<Color> GOOGLE_COLORS =
            ImmutableSet.<Color>builder()
                    //.addAll(WEBSAFE_COLORS)
                    .add(new Color(0, 191, 255))
                    .build();

    /**
     * 此外，对有序不可变集合来说，排序是在构造集合的时候完成的，如：
     * ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
     * 会在构造时就把元素排序为a, b, c, d
     */

    /**
     * 所有不可变集合都有一个asList()方法提供ImmutableList视图，来帮助你用列表形式方便地读取集合元素。
     * 例如，你可以使用sortedSet.asList().get(k)从ImmutableSortedSet中读取第k个最小元素
     */

}