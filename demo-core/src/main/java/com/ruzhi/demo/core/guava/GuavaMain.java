/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ruzhi.demo.core.guava;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.python.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author chunlong.wchl
 * @version $Id: GuavaMain.java, v 0.1 2016-11-22 下午9:28 chunlong.wchl Exp $$
 */
public class GuavaMain {
    private String aString = "aString";
    private int anInt = 1;

    /**
     * 最新api: http://google.github.io/guava/releases/snapshot/api/docs/index.html
     * 中文api: http://ifeve.com/google-guava/
     */
    public void main(String args[]) {

        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true

        // Returns "ClassName{x=1}"
        Objects.toStringHelper(this).add("x", 1).toString();
        // Returns "MyObject{x=1}"
        Objects.toStringHelper("MyObject").add("x", 1).toString();


    }

    /**
     * 前置条件：让方法调用的前置条件判断更简单。
     * <p>
     * Guava在Preconditions类中提供了若干前置条件判断的实用方法，我们强烈建议在Eclipse中静态导入这些方法。每个方法都有三个变种：
     * <p>
     * 没有额外参数：抛出的异常中没有错误消息；
     * 有一个Object对象作为额外参数：抛出的异常使用Object.toString() 作为错误消息；
     * 有一个String对象作为额外参数，并且有一组任意数量的附加Object对象：这个变种处理异常消息的方式有点类似printf，但考虑GWT的兼容性和效率，只支持%s指示符
     * <p>
     * checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
     * checkArgument(i < j, "Expected i < j, but %s > %s", i, j);
     */
    private void testPreconditions() {
        checkArgument(1 == 1);
        checkNotNull(this);
    }

    /**
     * 实现一个比较器[Comparator]，或者直接实现Comparable接口有时也伤不起
     * ComparisonChain执行一种懒比较：它执行比较操作直至发现非零的结果，在那之后的比较输入将被忽略
     */
    private int compareTo(GuavaMain that) {
        return ComparisonChain.start()
                .compare(this.aString, that.aString)
                .compare(this.anInt, that.anInt)
                //.compare(this.anEnum, that.anEnum, Ordering.natural().nullsLast())
                .result();
    }

    /**
     * 一个比较器时
     */
    Ordering<GuavaMain> itemInfoForCardOrdering = new Ordering<GuavaMain>() {
        @Override
        public int compare(GuavaMain left, GuavaMain right) {
            return left.anInt - right.anInt;
        }
    };
    List<GuavaMain> guavaMains  = new ArrayList<>();
    List<GuavaMain> orderedGuavaMains = itemInfoForCardOrdering.sortedCopy(guavaMains);


}