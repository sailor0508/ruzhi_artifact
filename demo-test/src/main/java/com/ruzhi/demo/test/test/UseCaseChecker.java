package com.ruzhi.demo.test.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用例检测的注解处理器
 *
 * @author <a href="mailto:cn.java.river@gmail.com">Tao</a>
 * @since 1.0
 */
public class UseCaseChecker {

    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            MyUseCase uc = m.getAnnotation(MyUseCase.class);
            if (uc != null) {
                System.out.println("找到用例:" + uc.id() + " " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for (int i : useCases) {
            System.out.println("警告-- 缺失用例:" + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 1, 2, 3, 4, 47);
        trackUseCases(useCases, PasswordUtils.class);
    }

}
/**
 * 运行输出结果为：
 * 找到用例:1 密码必须包含至少一个数字
 * 找到用例:4 新密码不能和曾经用过的密码重复
 * 找到用例:3 无描述
 * 警告-- 缺失用例:2
 * 警告-- 缺失用例:5
 */