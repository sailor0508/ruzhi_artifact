//: annotations/InterfaceExtractorProcessorFactory.java
// APT-based annotation processing.
package com.ruzhi.demo.util.apt.thinkInJava;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;

import java.util.*;

/**
 * apt ������Ҫһ����������Ϊ��ָ����ȷ�Ĵ�������Ȼ����ܵ��ô������ϵ� process ����
 */
public class InterfaceExtractorProcessorFactory implements AnnotationProcessorFactory {
    /**
     * ����ע�⴦����
     * atds �� ʹ��apt����ʱ �����java ��
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
