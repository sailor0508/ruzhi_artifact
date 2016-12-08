package com.ruzhi.demo.util.spring.quartz.simple;


import com.ruzhi.demo.util.groovy.IMyHelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by chunlong.wchl on 2015/6/3.
 * http://www.meiriyouke.net/?p=82
 */
public class TestQuartz {
    private static Logger log = LoggerFactory.getLogger(TestQuartz.class);
    int i = 0;

    @Resource
    private IMyHelloWorld myHelloWorld;

    public void doSomething(){
        i++;

        if (log.isInfoEnabled()) {
            log.info("TestQuartz开始执行 i:"+i);
            myHelloWorld.run();
        }
    }



}
