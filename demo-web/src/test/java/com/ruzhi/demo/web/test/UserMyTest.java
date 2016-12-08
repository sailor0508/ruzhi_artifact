package com.ruzhi.demo.web.test;

import com.ruzhi.demo.client.vo.DeliverVO;
import com.ruzhi.demo.client.service.IDeliverService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMyTest {
    private IDeliverService deliverService;

    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:demo-web_test.xml"});
        deliverService = (IDeliverService) context.getBean("deliverService");
    }

    @Test
    public void addUser(){
        DeliverVO deliverVO = new DeliverVO();
        deliverVO.setIdNo(118888888L);
        deliverVO.setName("1111你好你好你好你好");
        deliverVO.setSpId(111888888888888888L);
        System.out.println(deliverService.insertDeliver(deliverVO));
    }
}
