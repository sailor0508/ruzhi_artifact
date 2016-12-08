package com.ruzhi.demo.core.test;

import com.ruzhi.demo.common.BaseDO;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by chunlong.wchl on 2015/5/30.
 */
public class Auction extends BaseDO {
    private String name;
    private String price;
    private float weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     ToStringStyle参数说明：
         1. DEFAULT_STYLE
            com.entity.Person@182f0db[name=John Doe,age=33,smoker=false]
         2. MULTI_LINE_STYLE
             com.entity.Person@182f0db[
             name=John Doe
             age=33
             smoker=false
             ]
         3. NO_FIELD_NAMES_STYLE
             com.entity.Person@182f0db[John Doe,33,false]
         4. SHORT_PREFIX_STYLE   （即截去了包名）
            Person[name=John Doe,age=33,smoker=false]
         5. SIMPLE_STYLE
            John Doe,33,false
     * */
}
