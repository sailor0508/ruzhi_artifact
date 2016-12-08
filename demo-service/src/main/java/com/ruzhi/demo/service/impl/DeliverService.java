package com.ruzhi.demo.service.impl;


import com.ruzhi.demo.client.service.IDeliverService;
import com.ruzhi.demo.client.vo.DeliverVO;
import com.ruzhi.demo.core.postgre.mapper.DeliverMapper;
import com.ruzhi.demo.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeliverService implements IDeliverService {
    private static Logger log = LoggerFactory.getLogger(DeliverService.class);
    @Resource
    private DeliverMapper deliverMapper;
    /*@Resource
    private EmployeeService employeeService;*/

    public int insertDeliver(DeliverVO deliverVO) {
        log.error("=====================================");
        log.info("the current fethod is {}", "insertDeliver");
        log.warn("the current fethod is {},{}", "insertDeliver1", "insertDeliver2");
        log.error("the current fethod is {},{},{}", new String[]{"insertDeliver1", "insertDeliver2", "insertDeliver3"});
        log.error("=====================================");
        //employeeService.power(2,3);//这里不能new 需要从spring容器中取，否则日志拦截器aop不生效
        int b =  deliverMapper.insertSelective(ServiceUtil.transFromVo2Do(deliverVO));
        //employeeService.power(2,3);
        return b;
    }

}

