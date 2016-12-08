package com.ruzhi.demo.util.spring.quartz;

import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by chunlong.wchl on 2015/6/17.
 * Created by chunlong.wchl on 2015/6/16.
 * jobList就是我们需要的计划中的任务列表，需要注意一个job可能会有多个trigger的情况，
 * 在下面讲到的立即运行一次任务的时候，会生成一个临时的trigger也会出现在这。这里把一个Job有多个trigger的情况看成是多个任务。
 * 我们前面包括在实际项目中一般用到的都是CronTrigger ，所以这里我们着重处理了下CronTrigger的情况
 */
public class ScheduleJobUtils {
    private static final Logger log = LoggerFactory.getLogger(ScheduleJobUtils.class);

    public static List<ScheduleJob> searchStandForJob(Scheduler scheduler) throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                ScheduleJob job = new ScheduleJob();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     * 运行中的任务
     * @throws SchedulerException
     */
    public static List<ScheduleJob> searchRunningJob(Scheduler scheduler) throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            ScheduleJob job = new ScheduleJob();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return  jobList;
    }
    public static ScheduleJob genScheduleJob(ScheduleJobVo scheduleJobVo) {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setJobName(scheduleJobVo.getJobName());
        scheduleJob.setJobGroup(scheduleJobVo.getJobGroup());
        scheduleJob.setScheduleJobId(scheduleJobVo.getScheduleJobId());
        scheduleJob.setCronExpression(scheduleJobVo.getCronExpression());
        scheduleJob.setIsSync(scheduleJobVo.getIsSync());
        return scheduleJob;
    }
    /**
     * 更新任务的时间表达式
     * 更新之后，任务将立即按新的时间表达式执行：
     * 假设我们更新了任务的时间表达式，任务已按新的时间表达式在执行，但在获取到参数后发现时间表达式还是原来的
     */
    public static void updateJob(ScheduleJob scheduleJob,Scheduler scheduler) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        //按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        //按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
    }
    /**
     * 创建定时任务
     */
    public static Long  createScheduleJob(Scheduler scheduler, String jobName, String jobGroup, String cronExpression, boolean isSync, Object param) throws SchedulerException {
        //同步或异步
        Class<? extends Job> jobClass = isSync ? JobSyncFactory.class : JobFactory.class;
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).build();
        //放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put("scheduleJob", param);
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("创建定时任务失败", e);
            throw new SchedulerException("创建定时任务失败");
        }
        return 111L;
    }
    /**
     * 获取表达式触发器
     * @param scheduler the scheduler
     * @param jobName the job name
     * @param jobGroup the job group
     * @return cron trigger
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobName, String jobGroup) throws SchedulerException {

        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            return (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            log.error("获取定时任务CronTrigger出现异常", e);
            throw new SchedulerException("获取定时任务CronTrigger出现异常");
        }
    }
    public static ScheduleJob getScheduleJob(String groupName,String jobName,Scheduler scheduler){
        ScheduleJob scheduleJobTemp = new ScheduleJob();
        List<ScheduleJob> scheduleJobs = null;
        try {
            scheduleJobs = ScheduleJobUtils.searchStandForJob(scheduler);
        } catch (SchedulerException e) {
            log.warn(e.toString());
            return null;
        }
        for (ScheduleJob scheduleJob : scheduleJobs) {
            if(StringUtils.equals(groupName,scheduleJob.getJobGroup()) && StringUtils.equals(jobName,scheduleJob.getJobName())){
                scheduleJobTemp =  scheduleJob;
                break;
            }
        }
        return  scheduleJobTemp;
    }
    /**
     * 获取jobKey
     *
     * @param jobName the job name
     * @param jobGroup the job group
     * @return the job key
     */
    public static JobKey getJobKey(String jobName, String jobGroup) {
        return JobKey.jobKey(jobName, jobGroup);
    }
    /**
     * 获取触发器key
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
        return TriggerKey.triggerKey(jobName, jobGroup);
    }
}
