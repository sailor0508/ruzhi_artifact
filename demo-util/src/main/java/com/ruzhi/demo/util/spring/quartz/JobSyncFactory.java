package com.ruzhi.demo.util.spring.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 同步的任务工厂类
 *
 没有@DisallowConcurrentExecution 我们实现的是无状态的Job，如果要实现有状态的Job在以前是实现StatefulJob接口，在我使用的quartz 2.2.1中，
 StatefulJob接口已经不推荐使用了，换成了注解的方式，只需要给你实现的Job类加上注解@DisallowConcurrentExecution即可实现有状态
 */
@DisallowConcurrentExecution
public class JobSyncFactory implements Job {
    private static Logger log = LoggerFactory.getLogger(JobSyncFactory.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        log.info("任务名称 = [" + scheduleJob.getJobName() + "]");
    }
}
