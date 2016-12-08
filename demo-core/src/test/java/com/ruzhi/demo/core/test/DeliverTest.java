package com.ruzhi.demo.core.test;

import ch.qos.logback.classic.Logger;
import com.ruzhi.demo.core.postgre.mapper.DeliverMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by chunlong.wchl on 2015/4/17.
 */
public class DeliverTest {
    private static org.slf4j.Logger log = LoggerFactory.getLogger(DeliverTest.class);
    static DeliverMapper deliverMapper;
    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:demo-core_test.xml");
            deliverMapper = (DeliverMapper) context.getBean("deliverMapper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void  modelTest(){
        log.info("Start to verify User [{}]", "sailor");
        Deliver deliver = new Deliver();
        //deliver.setId(1234L);
        deliver.setIdNo(44441100000L);
        deliver.setName("33");
        deliver.setSpId(4440L);
        deliver.setGmtCreate(new Date());
        deliver.setGmtModified(new Date());
        deliver.setStatus(1);
        deliver.setPhone("44442000");

        deliverMapper.insertSelective(deliver);
    }
}
