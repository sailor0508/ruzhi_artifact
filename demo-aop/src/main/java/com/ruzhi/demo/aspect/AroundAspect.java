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
 * 该类为我们关注的类进行代理，目的是当目标类有bug的时候，我们能通过drm配置绕过有问题的类，并使用oss中的新类代替之，所以需要先上传文件到oss上，
 * 20秒（OssScanner 定时扫描时间）后推送drm配置
 * <p>
 * 注意：没有修复完bug并上线后，需要把drm配置修改一下，或者删除oss /home/admin/groovy/kbsearch_v1/kbsearch_dynamic_jar 中的文件
 * drm 配置列子："newClasses":"com.alipay.kbsearch.biz.msgbroker.LbsWifiCheckMsgProcessor"
 *
 * @author chunlong.wchl
 * @version $Id: AroundAspect.java, v 0.1 2016-11-08 下午6:16 chunlong.wchl Exp $$
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
            //如果drm 中配置的需要被替换的类型 和 当前运行的类 想吻合 则 替换之，并保存到本地缓存中
            if (MapUtils.isNotEmpty(newClasses) && newClasses.containsKey(currentClassInfo.getClassAbsoluteName())) {

                try {
                    //加载新类
                    parserCurrentClassInfo(currentClassInfo, proceedingJoinPoint, true);

                    Class newClass = generatorNewClass(newClasses, currentClassInfo);
                    //执行新类中的方法
                    doExcuteByNewClass(newClass, currentClassInfo);

                } catch (Exception e) {
                    //执行新类中方法出错时，则执行老方法
                    doExcuteByJoinPoint(proceedingJoinPoint, currentClassInfo.getArgs());
                }

            } else {
                //则执行老方法
                value = doExcuteByJoinPoint(proceedingJoinPoint, currentClassInfo.getArgs());
            }

        } catch (Throwable e) {
        }

        return value;
    }

    /**
     * 执行被替换后的方法
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
         * 注意：从spring 容器中获取属性值得方式是不可取的，因为属性可能就没有beanName
         * 所以只能从老的对象中进行copy，这就要求老的对象有get、set方法 （这也就在一定程度上对现有的业务代码产生了侵入）
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
     * 从新实例中获取 Method对象
     * <p>
     * 注意：不能使用 切面结果的对象中获取的Method对象进行invoke ，否则会报异常
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
     * 执行原始方法
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
     * 加载指定的文件 （drm中配置的文件 load 到本地机器）
     * TODO 需要先从缓存中获取，如果没有再去load
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
     * 生成能找到本地 class 文件的路径
     *
     * @param newClassSimpleName
     * @return
     */
    private String getLocalAbsoluteClassPath(String newClassSimpleName) {
        return localFolderAbsolutePath + File.separator + newClassSimpleName;
    }

    /**
     * 获取当前切面截获的方法及所在类信息
     *
     * @param proceedingJoinPoint
     * @param isFullParser        为 false 则只解析 classAbsoluteName / args 字段，出于对性能考虑
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
     * 从 切面信息中 获取 当前 运行的 Class 对象
     *
     * @param proceedingJoinPoint
     * @return
     */
    private Object parserCurrentClass(ProceedingJoinPoint proceedingJoinPoint) {
        return proceedingJoinPoint.getTarget();
    }

    /**
     * 从 切面信息中 获取 当前 运行的 Method 对象
     *
     * @param proceedingJoinPoint
     * @return
     */
    private Method parserCurrentMethod(ProceedingJoinPoint proceedingJoinPoint) {

        Signature signature = proceedingJoinPoint.getSignature();

        //注意：Method m =  (MethodSignature) signature.getMethod();
        // 这种方式获取到的方法是接口的方法而不是具体的实现类的方法，因此是错误的。

        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
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
     * map<老java类绝对路径,新java类简单名字>
     * <p>
     * 老java类绝对路径:用于寻找需要被替换的有bug的类
     * 新java类简单名字：用于从本地加载新类（路径在配置文件中配置好了：/home/admin/groovy/kbsearch_v1/kbsearch_dynamic_jar）
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
     * 替换后的java文件路径+名字,多个之间用分号分割，格式如下
     * com.alipay.kbsearch.biz.rpc.impl.DrmConfig.java；com.alipay.kbsearch.biz.rpc.impl.Configurations
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