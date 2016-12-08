package com.ruzhi.demo.client.vo;

import com.ruzhi.demo.common.BaseVO;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by chunlong.wchl on 2015/4/23.
 * bean对象的构建要考虑使用构造者模式
 * 属性的类型要考虑是否要用包装类型
 */
public class DeliverVO extends BaseVO {
    private Long id;

    private String name;

    private Long idNo;

    private Long spId;

    private Integer status;

    private String phone;
    @JsonIgnore
    private String passWord;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getIdNo() {
        return idNo;
    }

    public void setIdNo(Long idNo) {
        this.idNo = idNo;
    }

    public Long getSpId() {
        return spId;
    }

    public void setSpId(Long spId) {
        this.spId = spId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}
