//: annotations/InterfaceExtractorProcessorFactory.java
// APT-based annotation processing.
package com.ruzhi.demo.util.apt.thinkInJava;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;

import java.util.*;

/**
 * apt 工具需要一个工程类来为其指明正确的处理器，然后才能调用处理器上的 process 方法
 */
public class InterfaceExtractorProcessorFactory implements AnnotationProcessorFactory {
    /**
     * 返回注解处理器
     * atds ： 使用apt工具时 传入的java 类
     */
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
        return new InterfaceExtractorProcessor(env);
    }

    public Collection<String> supportedAnnotationTypes() {
        return Collections.singleton("annotations.ExtractInterface");
    }

    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }
} ///:~
