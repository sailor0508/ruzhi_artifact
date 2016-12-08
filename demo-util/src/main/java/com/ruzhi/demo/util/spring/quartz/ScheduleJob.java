package com.ruzhi.demo.util.spring.quartz;

import java.io.Serializable;
import java.util.Date;

/**
 * 计划任务信息
 *
 既然要动态的创建任务，我们的任务信息当然要保存在某个地方了，
 这里我们新建一个保存任务信息对应的实体类
 */
public class ScheduleJob implements Serializable{

    private static final long serialVersionUID = 4888005949821878223L;

    /** 任务id */
    private Long              scheduleJobId;

    /** 任务名称 */
    private String            jobName;

    /** 任务别名 */
    private String            aliasName;

    /** 任务分组 */
    private String            jobGroup;

    /** 触发器 */
    private String            jobTrigger;

    /** 任务状态 */
    private String            status;

    /** 任务运行时间表达式 */
    private String            cronExpression;

    /** 是否异步 */
    private Boolean           isSync;

    /** 任务描述 */
    private String            description;

    /** 创建时间 */
    private Date              gmtCreate;

    /** 修改时间 */
    private Date              gmtModify;

    public Long getScheduleJobId() {
        return scheduleJobId;
    }

    public void setScheduleJobId(Long scheduleJobId) {
        this.scheduleJobId = scheduleJobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobTrigger() {
        return jobTrigger;
    }

    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Boolean getIsSync() {
        return isSync;
    }

    public void setIsSync(Boolean isSync) {
        this.isSync = isSync;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}
