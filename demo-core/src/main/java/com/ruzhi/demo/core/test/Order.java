package com.ruzhi.demo.core.test;

import com.ruzhi.demo.common.BaseDO;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by chunlong.wchl on 2015/5/30.
 */
public class Order extends BaseDO {
    private long orderNo;
    private float price;

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {//最好可以用反射机制，这样字段信息改动时，这里就不用改动了
        return new ToStringBuilder(this).append("orderNo", orderNo).append("price", price).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(3, 7).append(orderNo).append(price).toHashCode();
    }

    @Override
    public boolean equals(Object pObject) {
        boolean equals = false;
        if (pObject instanceof Order) {
            Order bean = (Order) pObject;
            equals = (new EqualsBuilder().append(orderNo, bean.orderNo).append(price, bean.price)).isEquals();
        }
        return equals;
    }

    public int compareTo(Object pObject) {
        return CompareToBuilder.reflectionCompare(this, pObject);
    }

}
