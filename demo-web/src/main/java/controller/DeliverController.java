package controller;

import com.ruzhi.demo.client.vo.DeliverVO;
import com.ruzhi.demo.client.service.IDeliverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/")
public class DeliverController {
    @Resource
    private IDeliverService deliverService;

    private static Logger log = LoggerFactory.getLogger(DeliverController.class);

    @RequestMapping("index")
    public String index(){
        log.warn("already step into index();");
        DeliverVO deliverVO = new DeliverVO();
        deliverVO.setIdNo(130622319870L);
        deliverVO.setName("setname");
        deliverVO.setSpId(2L);
        deliverVO.setGmtCreate(new Date());
        deliverVO.setGmtModified(new Date());
        deliverVO.setStatus(1);
        deliverVO.setPhone("1234567890");
        deliverService.insertDeliver(deliverVO);
        return "";
    }

}

