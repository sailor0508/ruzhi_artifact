package com.ruzhi.demo.util.spring.quartz;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chunlong.wchl on 2015/6/16.
 * http://www.dexcoder.com/blog/article/1822.shtml
 */
@Service
public class ScheduleJobService implements IScheduleJobService, Serializable {

    @Resource
    private Scheduler scheduler;

    private static Logger log = LoggerFactory.getLogger(ScheduleJobService.class);

    @Override
    public ScheduleJobVo get(String groupName,String jobName) {
      return VoTransformUtil.transFromScheduleJob2ScheduleJobVo(ScheduleJobUtils.getScheduleJob(groupName,jobName, scheduler));
    }

    @Override
    public Long insert(ScheduleJobVo scheduleJobVo) {
       return  createScheduleJob(scheduleJobVo);
    }

    /**
     * 创建任务
     */
    public Long createScheduleJob(ScheduleJobVo scheduleJobVo){
        ScheduleJob scheduleJob = ScheduleJobUtils.genScheduleJob(scheduleJobVo);
        try {
            return ScheduleJobUtils.createScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup(), scheduleJob.getCronExpression(), scheduleJob.getIsSync(), scheduleJob);
        } catch (SchedulerException e) {
            return  -1L;
        }
    }


    /**
     * 计划中的任务
     */
    @Override
    public List<ScheduleJobVo> queryRunningJobList() {
        try {
            List<ScheduleJobVo> scheduleJobVos = VoTransformUtil.transFromScheduleJobs2ScheduleJobVos(ScheduleJobUtils.searchRunningJob(scheduler));
            return  scheduleJobVos;
        } catch (SchedulerException e) {
            return  new ArrayList<ScheduleJobVo>();
        }
    }
    @Override
    public List<ScheduleJobVo> queryStandForJobList() {
        try {
            List<ScheduleJobVo> scheduleJobVos = VoTransformUtil.transFromScheduleJobs2ScheduleJobVos(ScheduleJobUtils.searchStandForJob(scheduler));
            return  scheduleJobVos;
        } catch (SchedulerException e) {
            return  new ArrayList<ScheduleJobVo>();
        }
    }

    /**
     * 暂停任务
     */
    @Override
    public void pauseJob(String groupName,String jobName) {
        ScheduleJob scheduleJob = ScheduleJobUtils.getScheduleJob(groupName,jobName, scheduler);
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     * 删除任务后，所对应的trigger也将被删除
     */
    public void delete(String groupName,String jobName){
        ScheduleJob scheduleJob = ScheduleJobUtils.getScheduleJob(groupName,jobName,scheduler);
        try {
            scheduler.deleteJob(ScheduleJobUtils.getJobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup()));
        } catch (SchedulerException e) {
            log.error("删除定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     */
    @Override
    public void resumeJob(String groupName,String jobName){
        ScheduleJob scheduleJob = ScheduleJobUtils.getScheduleJob(groupName,jobName,scheduler);
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败", e);
        }
    }

    @Override
    public List<ScheduleJobVo> queryList(ScheduleJobVo scheduleJobVo) {
        return null;
    }

    /**
     * 立即运行任务,运行一次任务
     * 这里的立即运行，只会运行一次，方便测试时用。quartz是通过临时生成一个trigger的方式来实现的，
     * 这个trigger将在本次任务运行完成之后自动删除。trigger的key是随机生成的，例如：DEFAULT.MT_4k9fd10jcn9mg。
     * 在我的测试中，前面的DEFAULT.MT是固定的，后面部分才随机生成。
     * 需要注意在定时任务运行时更新是没有办法改变同步和异步的
     */
    @Override
    public void runOnce(String groupName,String jobName) {
        ScheduleJob scheduleJob = ScheduleJobUtils.getScheduleJob(groupName,jobName,scheduler);
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("运行一次定时任务失败", e);
        }
    }


    /**
     * 更新定时任务
     */
    @Override
    public void update(ScheduleJobVo scheduleJobVo) {
        try {
            ScheduleJobUtils.updateJob(VoTransformUtil.transFromScheduleJobVo2ScheduleJob(scheduleJobVo), scheduler);
        } catch (SchedulerException e) {
            return;
        }
    }

}
