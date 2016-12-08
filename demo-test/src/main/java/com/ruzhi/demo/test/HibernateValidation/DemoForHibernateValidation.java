package com.ruzhi.demo.test.HibernateValidation;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by chunlong.wchl on 2015/6/2.
 */
public class DemoForHibernateValidation {

    @NotBlank(message ="company is blank")
    private String company="";

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
