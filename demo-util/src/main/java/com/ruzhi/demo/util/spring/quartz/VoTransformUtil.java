package com.ruzhi.demo.util.spring.quartz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chunlong.wchl on 2015/6/17.
 */
public class VoTransformUtil {
    public static ScheduleJobVo transFromScheduleJob2ScheduleJobVo(ScheduleJob scheduleJob) {
        ScheduleJobVo scheduleJobVo = new ScheduleJobVo();
        if(scheduleJob == null){
            return scheduleJobVo;
        }
        scheduleJobVo.setJobName(scheduleJob.getJobName());
        scheduleJobVo.setStatus(scheduleJob.getStatus());
        scheduleJobVo.setDescription(scheduleJob.getDescription());
        scheduleJobVo.setCronExpression(scheduleJob.getCronExpression());
        scheduleJobVo.setIsSync(scheduleJob.getIsSync());
        scheduleJobVo.setJobGroup(scheduleJob.getJobGroup());
        scheduleJobVo.setJobTrigger(scheduleJob.getJobTrigger());
        scheduleJobVo.setGmtCreate(scheduleJob.getGmtCreate());
        scheduleJobVo.setGmtModify(scheduleJob.getGmtModify());
        return scheduleJobVo;
    }

    public static ScheduleJob transFromScheduleJobVo2ScheduleJob(ScheduleJobVo scheduleJobVo) {
        ScheduleJob scheduleJob = new ScheduleJob();
        if(scheduleJobVo == null){
            return scheduleJob;
        }
        scheduleJob.setJobName(scheduleJobVo.getJobName());
        scheduleJob.setStatus(scheduleJobVo.getStatus());
        scheduleJob.setDescription(scheduleJobVo.getDescription());
        scheduleJob.setCronExpression(scheduleJobVo.getCronExpression());
        scheduleJob.setIsSync(scheduleJobVo.getIsSync());
        scheduleJob.setJobGroup(scheduleJobVo.getJobGroup());
        scheduleJob.setJobTrigger(scheduleJobVo.getJobTrigger());
        scheduleJob.setGmtCreate(scheduleJobVo.getGmtCreate());
        scheduleJob.setGmtModify(scheduleJobVo.getGmtModify());
        return scheduleJob;
    }

    public static List<ScheduleJobVo> transFromScheduleJobs2ScheduleJobVos(List<ScheduleJob> scheduleJobs) {
        List<ScheduleJobVo> scheduleJobVos = new ArrayList<ScheduleJobVo>();
        if(scheduleJobs == null || scheduleJobs.isEmpty()){
            return  scheduleJobVos;
        }
        for (ScheduleJob scheduleJob : scheduleJobs) {
            scheduleJobVos.add(transFromScheduleJob2ScheduleJobVo(scheduleJob));
        }
        return scheduleJobVos;
    }
}
