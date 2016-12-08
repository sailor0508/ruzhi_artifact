package com.ruzhi.demo.test.test;

import com.ruzhi.demo.util.groovy.IMyHelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chunlong.wchl on 2015/6/25.
 */
public class GroovyTest {

    private IMyHelloWorld myHelloWorld;
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:demo-servier_test.xml"});
        myHelloWorld = (IMyHelloWorld) context.getBean("myHelloWorld");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addTest() {
        before();
        myHelloWorld.run();
    }
}
