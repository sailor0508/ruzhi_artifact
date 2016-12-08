package com.ruzhi.demo.util;

import com.ruzhi.demo.client.vo.DeliverVO;
import com.ruzhi.demo.core.test.Deliver;

/**
 * Created by chunlong.wchl on 2015/4/23.
 */
public class ServiceUtil {
    public static Deliver transFromVo2Do(DeliverVO deliverVo) {
        if (deliverVo == null) {
            return null;
        }
        Deliver deliver = new Deliver();
        deliver.setId(deliverVo.getId());
        deliver.setIdNo(deliverVo.getIdNo());
        deliver.setPhone(deliverVo.getPhone());
        deliver.setName(deliverVo.getName());
        deliver.setPassWord(deliverVo.getPassWord());
        return deliver;
    }
}
