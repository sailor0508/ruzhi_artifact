/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ruzhi.demo.aspect;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ����Ϊ���ǹ�ע������д���Ŀ���ǵ�Ŀ������bug��ʱ��������ͨ��drm�����ƹ���������࣬��ʹ��oss�е��������֮��������Ҫ���ϴ��ļ���oss�ϣ�
 * 20�루OssScanner ��ʱɨ��ʱ�䣩������drm����
 * <p>
 * ע�⣺û���޸���bug�����ߺ���Ҫ��drm�����޸�һ�£�����ɾ��oss /home/admin/groovy/kbsearch_v1/kbsearch_dynamic_jar �е��ļ�
 * drm �������ӣ�"newClasses":"com.alipay.kbsearch.biz.msgbroker.LbsWifiCheckMsgProcessor"
 *
 * @author chunlong.wchl
 * @version $Id: AroundAspect.java, v 0.1 2016-11-08 ����6:16 chunlong.wchl Exp $$
 */
@Aspect
public class AroundAspect {

    private static final String NEW_CLASSES = "newClasses";
    private static final String NEW_CLASSES_SPLIT = ";";
    private static final String FILE_PATH_SPLIT = ".";

    private String localFolderAbsolutePath;

    @Around("execution(* com.alipay.kbsearch.biz.msgbroker.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {

        Map<String, String> newClasses = parserOldAbsolutePathAndNewSimpleName();

        CurrentClassInfo currentClassInfo = new CurrentClassInfo();
        parserCurrentClassInfo(currentClassInfo, proceedingJoinPoint, false);

        Object value = null;
        try {
            //���drm �����õ���Ҫ���滻������ �� ��ǰ���е��� ���Ǻ� �� �滻֮�������浽���ػ�����
            if (MapUtils.isNotEmpty(newClasses) && newClasses.containsKey(currentClassInfo.getClassAbsoluteName())) {

                try {
                    //��������
                    parserCurrentClassInfo(currentClassInfo, proceedingJoinPoint, true);

                    Class newClass = generatorNewClass(newClasses, currentClassInfo);
                    //ִ�������еķ���
                    doExcuteByNewClass(newClass, currentClassInfo);

                } catch (Exception e) {
                    //ִ�������з�������ʱ����ִ���Ϸ���
                    doExcuteByJoinPoint(proceedingJoinPoint, currentClassInfo.getArgs());
                }

            } else {
                //��ִ���Ϸ���
                value = doExcuteByJoinPoint(proceedingJoinPoint, currentClassInfo.getArgs());
            }

        } catch (Throwable e) {
        }

        return value;
    }

    /**
     * ִ�б��滻��ķ���
     *
     * @param newClass
     * @param currentClassInfo
     * @return
     * @throws Exception
     */
    private Object doExcuteByNewClass(Class newClass, CurrentClassInfo currentClassInfo) throws Exception {

        if (newClass == null) {
            throw new Exception("newClass == null!");
        }
        if (currentClassInfo.getCurrentMethod() == null) {
            throw new Exception("currentClassInfo.getCurrentMethod() == null!");
        }

        Object dst = newClass.newInstance();
        /**
         * ע�⣺��spring �����л�ȡ����ֵ�÷�ʽ�ǲ���ȡ�ģ���Ϊ���Կ��ܾ�û��beanName
         * ����ֻ�ܴ��ϵĶ����н���copy�����Ҫ���ϵĶ�����get��set���� ����Ҳ����һ���̶��϶����е�ҵ�������������룩
         */
        PropertyUtils.copyProperties(dst, currentClassInfo.getCurrentClass());
        Method currentTargetMethod = getCurrentTargetMethodForNewClass(currentClassInfo.getCurrentMethod(), dst);

        if (currentClassInfo.getArgs() == null) {
            return currentTargetMethod.invoke(dst);
        } else {
            return currentTargetMethod.invoke(dst, currentClassInfo.getArgs());
        }
    }

    /**
     * ����ʵ���л�ȡ Method����
     * <p>
     * ע�⣺����ʹ�� �������Ķ����л�ȡ��Method�������invoke ������ᱨ�쳣
     *
     * @param currentMethod
     * @param dst
     * @return
     */
    private Method getCurrentTargetMethodForNewClass(Method currentMethod, Object dst) throws NoSuchMethodException {
        if (currentMethod.getParameterTypes() == null) {
            return dst.getClass().getMethod(currentMethod.getName());
        } else {
            return dst.getClass().getMethod(currentMethod.getName(), currentMethod.getParameterTypes());
        }
    }

    /**
     * ִ��ԭʼ����
     *
     * @param proceedingJoinPoint
     * @param args
     * @return
     * @throws Throwable
     */
    private Object doExcuteByJoinPoint(ProceedingJoinPoint proceedingJoinPoint, Object[] args) throws Throwable {
        if (proceedingJoinPoint == null) {
            throw new Exception("proceedingJoinPoint == null!");
        }
        if (args == null) {
            return proceedingJoinPoint.proceed();
        } else {
            return proceedingJoinPoint.proceed(args);
        }
    }

    /**
     * ����ָ�����ļ� ��drm�����õ��ļ� load �����ػ�����
     * TODO ��Ҫ�ȴӻ����л�ȡ�����û����ȥload
     *
     * @param newClasses
     * @return
     */
    private Class generatorNewClass(Map<String, String> newClasses, CurrentClassInfo currentClassInfo) throws ClassNotFoundException, MalformedURLException {
        String classSimpleName = newClasses.get(currentClassInfo.getClassAbsoluteName());
        URL[] urls = new URL[]{new URL("file:" + getLocalFolderAbsolutePath() + "/" + classSimpleName + "Bak.jar")};
        URLClassLoader urlClassLoader = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
        Class newClass = urlClassLoader.loadClass(currentClassInfo.getClassAbsoluteName() + "Bak");
        return newClass;
    }

    /**
     * �������ҵ����� class �ļ���·��
     *
     * @param newClassSimpleName
     * @return
     */
    private String getLocalAbsoluteClassPath(String newClassSimpleName) {
        return localFolderAbsolutePath + File.separator + newClassSimpleName;
    }

    /**
     * ��ȡ��ǰ����ػ�ķ�������������Ϣ
     *
     * @param proceedingJoinPoint
     * @param isFullParser        Ϊ false ��ֻ���� classAbsoluteName / args �ֶΣ����ڶ����ܿ���
     * @return
     */
    private CurrentClassInfo parserCurrentClassInfo(CurrentClassInfo currentClassInfo, ProceedingJoinPoint proceedingJoinPoint, boolean isFullParser) {

        if (proceedingJoinPoint == null) {
            return currentClassInfo;
        }
        currentClassInfo.setClassAbsoluteName(proceedingJoinPoint.getTarget().getClass().getName());
        currentClassInfo.setArgs(proceedingJoinPoint.getArgs());

        if (isFullParser) {
            currentClassInfo.setCurrentMethod(parserCurrentMethod(proceedingJoinPoint));
            currentClassInfo.setCurrentClass(parserCurrentClass(proceedingJoinPoint));
        }

        return currentClassInfo;
    }

    /**
     * �� ������Ϣ�� ��ȡ ��ǰ ���е� Class ����
     *
     * @param proceedingJoinPoint
     * @return
     */
    private Object parserCurrentClass(ProceedingJoinPoint proceedingJoinPoint) {
        return proceedingJoinPoint.getTarget();
    }

    /**
     * �� ������Ϣ�� ��ȡ ��ǰ ���е� Method ����
     *
     * @param proceedingJoinPoint
     * @return
     */
    private Method parserCurrentMethod(ProceedingJoinPoint proceedingJoinPoint) {

        Signature signature = proceedingJoinPoint.getSignature();

        //ע�⣺Method m =  (MethodSignature) signature.getMethod();
        // ���ַ�ʽ��ȡ���ķ����ǽӿڵķ��������Ǿ����ʵ����ķ���������Ǵ���ġ�

        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("��ע��ֻ�����ڷ���");
        }

        MethodSignature methodSignature = (MethodSignature) signature;
        Object target = proceedingJoinPoint.getTarget();
        Method currentMethod = null;
        try {
            currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        } catch (NoSuchMethodException e) {
        }
        return currentMethod;

    }

    /**
     * map<��java�����·��,��java�������>
     * <p>
     * ��java�����·��:����Ѱ����Ҫ���滻����bug����
     * ��java������֣����ڴӱ��ؼ������ࣨ·���������ļ������ú��ˣ�/home/admin/groovy/kbsearch_v1/kbsearch_dynamic_jar��
     */
    private Map<String, String> parserOldAbsolutePathAndNewSimpleName() {


        Map<String, String> oldAbsolutePathAndNewSimpleName = new HashMap<String, String>();

        try {
            String newClassesAbsolutePath = "";//Configurations.getSwitchFlag(NEW_CLASSES);

            if (StringUtils.isBlank(newClassesAbsolutePath)) {
                return oldAbsolutePathAndNewSimpleName;
            }

            List<String> oldAbsolutePaths = Arrays.asList(newClassesAbsolutePath.split(NEW_CLASSES_SPLIT));

            for (String oldAbsolutePath : oldAbsolutePaths) {

                String simpleName = getSimpleName(oldAbsolutePath);
                if (StringUtils.isNotBlank(simpleName)) {
                    oldAbsolutePathAndNewSimpleName.put(oldAbsolutePath, simpleName);
                }
            }
        } catch (Exception e) {
        }

        return oldAbsolutePathAndNewSimpleName;
    }

    /**
     * �滻���java�ļ�·��+����,���֮���÷ֺŷָ��ʽ����
     * com.alipay.kbsearch.biz.rpc.impl.DrmConfig.java��com.alipay.kbsearch.biz.rpc.impl.Configurations
     *
     * @param oldAbsolutePath
     * @return
     */
    private String getSimpleName(String oldAbsolutePath) {
        if (StringUtils.isBlank(oldAbsolutePath) || !StringUtils.contains(oldAbsolutePath, FILE_PATH_SPLIT)) {
            return "";
        }
        String[] pathNameAndFileSuffix = oldAbsolutePath.split("\\" + FILE_PATH_SPLIT);

        int size = pathNameAndFileSuffix.length;

        if (size < 2) {
            return "";
        } else {
            return pathNameAndFileSuffix[size - 1];
        }
    }


    public void setLocalFolderAbsolutePath(String localFolderAbsolutePath) {
        this.localFolderAbsolutePath = localFolderAbsolutePath;
    }

    public String getLocalFolderAbsolutePath() {
        return localFolderAbsolutePath;
    }

}