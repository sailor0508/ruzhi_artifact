import com.ruzhi.demo.client.vo.DeliverVO;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.util.Date;

/**
 * Created by chunlong.wchl on 2015/5/28.
 */
public class JsonTest {

    public static void main(String[] args) {

        writeJsonObject();

        readJsonObject();
    }

    // 直接写入一个对象(所谓序列化)
    public static void writeJsonObject() {
        ObjectMapper mapper = new ObjectMapper();
        DeliverVO deliverVO = new DeliverVO();
        deliverVO.setIdNo(4444664L);
        deliverVO.setName("4444setname222766");
        deliverVO.setSpId(444442266L);
        deliverVO.setGmtCreate(new Date());
        deliverVO.setGmtModified(new Date());
        deliverVO.setStatus(1);
        deliverVO.setPhone("444477444");
        deliverVO.setPassWord("333333333333");
        System.out.println(deliverVO.toString());
        try {
            mapper.writeValue(new File("d:/test/person.json"), deliverVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 直接将一个json转化为对象（所谓反序列化）
    public static void readJsonObject() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            DeliverVO person = mapper.readValue(new File("d:/test/person.json"), DeliverVO.class);
            System.out.println(person.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
