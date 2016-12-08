package com.ruzhi.demo.util.spring.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by chunlong.wchl on 2015/6/17.
 */
@Component
public class JobList {
    private static Logger log = LoggerFactory.getLogger(JobList.class);
    int i = 0;
    public void doSomething(){
        i++;
        if (log.isInfoEnabled()) {
            log.info("doSomething run once 0000000000000000----------i:"+i);
        }
    }
    public void doSomething1(){
        i++;
        if (log.isInfoEnabled()) {
            log.info("doSomething111111111111111111111----------i:"+i);
        }
    }
    public void doSomething2(){
        i++;
        if (log.isInfoEnabled()) {
            log.info("doSomething22222222222222222222----------i:"+i);
        }
    }
    public void doSomething3(){
        i++;
        if (log.isInfoEnabled()) {
            log.info("doSomething33333333333333333333----------i:"+i);
        }
    }
    public void doSomething4(){
        i++;
        if (log.isInfoEnabled()) {
            log.info("doSomething44444444444444444444----------i:"+i);
        }
    }
}
