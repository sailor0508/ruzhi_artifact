/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ruzhi.demo.aspect;

import java.lang.reflect.Method;

/**
 * ��ǰ�������е� ���������������Ϣ
 *
 * @author chunlong.wchl
 * @version $Id: CurrentClassInfo.java, v 0.1 2016-11-09 ����3:20 chunlong.wchl Exp $$
 */
public class CurrentClassInfo {

    /**
     * �����·��
     */
    private String classAbsoluteName;
    /**
     * ������
     */
    private String classSimpleName;

    /**
     * ����
     */
    private Method currentMethod;

    /**
     * ��ǰ��ʵ��
     */
    private Object currentClass;

    /**
     * ����
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