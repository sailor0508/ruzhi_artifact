package com.ruzhi.demo.util.spring.quartz;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chunlong.wchl on 2015/6/16.
 */
public interface IScheduleJobService{
    /**
     * 新增
     */
    public Long insert(ScheduleJobVo scheduleJobVo);

    /**
     * 直接修改 只能修改运行的时间，参数、同异步等无法修改
     */
    public void update(ScheduleJobVo scheduleJobVo);

    /**
     * 删除
     */
    public void delete(String groupName,String jobName);

    /**
     * 运行一次任务
     */
    public void runOnce(String groupName,String jobName);

    /**
     * 暂停任务
     */
    public void pauseJob(String groupName,String jobName);

    /**
     * 恢复任务
     */
    public void resumeJob(String groupName,String jobName);

    /**
     * 获取任务对象
     */
    public ScheduleJobVo get(String groupName,String jobName);

    /**
     * 查询所有的任务列表
     */
    public List<ScheduleJobVo> queryList(ScheduleJobVo scheduleJobVo);

    /**
     * 获取运行中的任务列表
     */
    List<ScheduleJobVo> queryRunningJobList();

    /**
     * 获取计划中的任务列表
     */
    List<ScheduleJobVo> queryStandForJobList();
}
