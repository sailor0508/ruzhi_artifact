/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ruzhi.demo.aspect;

import java.lang.reflect.Method;

/**
 * 当前正在运行的 方法及所在类的信息
 *
 * @author chunlong.wchl
 * @version $Id: CurrentClassInfo.java, v 0.1 2016-11-09 下午3:20 chunlong.wchl Exp $$
 */
public class CurrentClassInfo {

    /**
     * 类绝对路径
     */
    private String classAbsoluteName;
    /**
     * 类名字
     */
    private String classSimpleName;

    /**
     * 方法
     */
    private Method currentMethod;

    /**
     * 当前类实例
     */
    private Object currentClass;

    /**
     * 参数
     */
    private Object[] args;

    public String getClassAbsoluteName() {
        return classAbsoluteName;
    }

    public void setClassAbsoluteName(String classAbsoluteName) {
        this.classAbsoluteName = classAbsoluteName;
    }

    public String getClassSimpleName() {
        return classSimpleName;
    }

    public void setClassSimpleName(String classSimpleName) {
        this.classSimpleName = classSimpleName;
    }

    public Method getCurrentMethod() {
        return currentMethod;
    }

    public void setCurrentMethod(Method currentMethod) {
        this.currentMethod = currentMethod;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(Object currentClass) {
        this.currentClass = currentClass;
    }
}