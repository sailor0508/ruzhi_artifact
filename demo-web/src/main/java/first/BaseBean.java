package first;

import com.ruzhi.demo.util.spring.quartz.ScheduleJobVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chunlong.wchl on 2015/6/17.
 */
public class BaseBean implements Serializable {

    protected String storeId = "";
    protected String shopUrl = "";
    protected String message = "";
    ///////////////////////////////////////
    protected List<ScheduleJobVo> runningScheduleJobVos = new ArrayList<ScheduleJobVo>();
    protected List<ScheduleJobVo> standForScheduleJobVos = new ArrayList<ScheduleJobVo>();
    protected String cronExpression = "";
    protected String description = "";
    protected String jobGroup = "";
    protected String jobName = "";
    protected ScheduleJobVo scheduleJobVo;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ScheduleJobVo> getRunningScheduleJobVos() {
        return runningScheduleJobVos;
    }

    public void setRunningScheduleJobVos(List<ScheduleJobVo> runningScheduleJobVos) {
        this.runningScheduleJobVos = runningScheduleJobVos;
    }

    public List<ScheduleJobVo> getStandForScheduleJobVos() {
        return standForScheduleJobVos;
    }

    public void setStandForScheduleJobVos(List<ScheduleJobVo> standForScheduleJobVos) {
        this.standForScheduleJobVos = standForScheduleJobVos;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public ScheduleJobVo getScheduleJobVo() {
        return scheduleJobVo;
    }

    public void setScheduleJobVo(ScheduleJobVo scheduleJobVo) {
        this.scheduleJobVo = scheduleJobVo;
    }
}
