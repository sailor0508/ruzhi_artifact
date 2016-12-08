package com.ruzhi.demo.util.spring.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 定时任务运行工厂类
 *
 没有@DisallowConcurrentExecution 我们实现的是无状态的Job，如果要实现有状态的Job在以前是实现StatefulJob接口，在我使用的quartz 2.2.1中，
 StatefulJob接口已经不推荐使用了，换成了注解的方式，只需要给你实现的Job类加上注解@DisallowConcurrentExecution即可实现有状态
 */
public class JobFactory implements Job {
    private static Logger log = LoggerFactory.getLogger(JobFactory.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        String className = scheduleJob.getJobGroup();
        String methodName = scheduleJob.getJobName();
        doTheJob(className,methodName);
        log.info("任务名称 = [" + className+"."+methodName + "]");
    }

    /**
     * run    className.methodName
     */
    private static void doTheJob(String className,String methodName){
        try{
            Class<?> classtype = Class.forName(className);
            Object jobClass = classtype.newInstance();
            Method method = classtype.getMethod(methodName);
            method.invoke(jobClass);
        }catch (Exception e){
            log.warn(e.toString());
        }

    }
    public void test(){
        System.out.println("aaaaaaaaaa");
        System.out.println("bbbbbbbbb");
    }
    public static  void main(String args[]){
        doTheJob("com.ruzhi.demo.util.spring.quartz.JobFactory","test");
    }
}
