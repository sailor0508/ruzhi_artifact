package com.ruzhi.demo.test.all.one;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * 被标记的注解方法
 * Created by sxf on 15-3-15.
 */
public class MyAnnotatedMethod {
    private ExecutableElement annotatedMethodElement;
    private String simpleMethodName;
    private String simpleClassName;

    private Class returnsType;
    private Class[] paramsType;

    public MyAnnotatedMethod(ExecutableElement annotatedMethodElement) {
        this.annotatedMethodElement = annotatedMethodElement;
        simpleMethodName = annotatedMethodElement.getSimpleName().toString();
        TypeElement parent = (TypeElement) annotatedMethodElement.getEnclosingElement();
        simpleClassName = parent.getQualifiedName().toString();

    }

    public ExecutableElement getAnnotatedMethodElement() {
        return annotatedMethodElement;
    }

    public String getSimpleMethodName() {
        return simpleMethodName;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }
}
