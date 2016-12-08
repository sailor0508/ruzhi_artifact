package com.ruzhi.demo.service.test;

import com.ruzhi.demo.client.vo.DeliverVO;
import com.ruzhi.demo.client.service.IDeliverService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by chunlong.wchl on 2015/4/23.
 */
public class DeliverServiceTest {
    private IDeliverService deliverService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:demo-servier_test.xml"});
        deliverService = (IDeliverService) context.getBean("deliverService");
    }

    @Test(timeout = 1000)
    public void addTest() {
        DeliverVO deliverVO = new DeliverVO();
        deliverVO.setIdNo(4444664L);
        deliverVO.setName("4444setname222766");
        deliverVO.setSpId(444442266L);
        deliverVO.setGmtCreate(new Date());
        deliverVO.setGmtModified(new Date());
        deliverVO.setStatus(1);
        deliverVO.setPhone("444477444");
        deliverService.insertDeliver(deliverVO);
    }

}
