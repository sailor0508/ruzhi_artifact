package first;

import com.ruzhi.demo.client.vo.DeliverVO;
import com.ruzhi.demo.client.service.IDeliverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;

@ManagedBean(name = "dataBean")
@SessionScoped
public class DataBean extends BaseBean{
    private static Logger log = LoggerFactory.getLogger(DataBean.class);

    @ManagedProperty(value = "#{deliverService}")
    private IDeliverService deliverService;//这里需要使用接口来生命，否则会报错

    private void init() {
        storeId = "";
        initResult();
    }
    private void initResult(){
        shopUrl = "";
        message = "";
    }

    public String lookShop() {
        init();
        message = String.valueOf(addUser());
        shopUrl = message;
        return "lookShop.xhtml";
    }
    public int addUser(){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int second = c.get(Calendar.SECOND);
        int minute = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        long IdNo = second * minute * hour;
        log.info(IdNo+"IdNo the current fethod is {}","addUser");
        log.warn(IdNo+"the current fethod is {},{}", "addUser1","addUser2");
        log.error(IdNo+"the current fethod is {},{},{}", new String[]{"addUser1","addUser2","addUser3"});

        DeliverVO deliverVO = new DeliverVO();
        deliverVO.setIdNo(IdNo);
        deliverVO.setName("你好你好你好你好" + IdNo);
        deliverVO.setSpId(IdNo);
        deliverVO.setPassWord("password1111");
        int a = deliverService.insertDeliver(deliverVO);
        Long id = deliverVO.getId();
        System.out.println(a);
        return  a == 1? (int)IdNo:a;
    }

}

