package first;

import com.ruzhi.demo.util.spring.quartz.IScheduleJobService;
import com.ruzhi.demo.util.spring.quartz.ScheduleJobVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.util.Date;

/**
 * 测试步骤：
 1、访问http://localhost:8080/pages/quartzJobBean.xhtml
 2、点击两次add按钮，然后flash按钮，同时看控制台，应该有三个定时任务（系统启动时自动创建一个，每次add产生一个）
 3、点击run按钮，查看控制台，应该立刻有个定时任务被触发
 3、点击modify按钮，查看控制台，应该有个任务的执行时间被修改为2秒执行一次
 4、点击pause按钮，查看控制台，应该刚才那个任务不再执行
 5、点击del按钮，然后flash按钮，发现页面少了一个定时任务
 */
@ManagedBean(name = "quartzJobBean")
@SessionScoped
public class QuartzJobBean extends BaseBean {
    private static Logger log = LoggerFactory.getLogger(QuartzJobBean.class);
    String groupName = "com.ruzhi.demo.util.spring.quartz.JobList";
    String methodName = "doSomething";


    @ManagedProperty(value = "#{scheduleJobService}")
    private IScheduleJobService scheduleJobService;//这里需要使用接口来生命，否则会报错
    int i = 1;
    private void init() {
        log.info("init.....................");
    }
    
    public String flash(){
        runningScheduleJobVos = scheduleJobService.queryRunningJobList();
        standForScheduleJobVos = scheduleJobService.queryStandForJobList();
        log.info(".....................flash successful...");
        return "quartzJobBean.xhtml";
    }
    public String add() {
        init();
        ScheduleJobVo scheduleJobVo = new ScheduleJobVo();
        scheduleJobVo.setJobName(methodName+i);
        scheduleJobVo.setJobGroup(groupName);
        int j = 10+i*5;
        scheduleJobVo.setCronExpression("0/"+j+" * * * * ?");//           "0/10 * * * * ?"
        scheduleJobVo.setIsSync(false);
        scheduleJobVo.setGmtModify(new Date());
        scheduleJobService.insert(scheduleJobVo);
        i++;
        return "quartzJobBean.xhtml";
    }

    public String runOnce(ActionEvent actionEvent){
        scheduleJobService.runOnce(groupName, methodName+1);
        log.info("runOnce...............");
        return "quartzJobBean.xhtml";
    }
    public String pause(){
        scheduleJobService.pauseJob(groupName, methodName+1);
        log.info("pause...............");
        return "quartzJobBean.xhtml";
    }
    public String del(){
        scheduleJobService.delete(groupName, methodName+1);
        log.info("del...............");
        return "quartzJobBean.xhtml";
    }
    public String modify(){
        ScheduleJobVo scheduleJobVo = new ScheduleJobVo();
        scheduleJobVo.setJobGroup(groupName);
        scheduleJobVo.setJobName(methodName+1);
        scheduleJobVo.setCronExpression("0/2 * * * * ?");
        scheduleJobVo.setDescription(description);

        scheduleJobService.update(scheduleJobVo);
        log.info("modify...............");
        return "quartzJobBean.xhtml";
    }

    public IScheduleJobService getScheduleJobService() {
        return scheduleJobService;
    }

    public void setScheduleJobService(IScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}

