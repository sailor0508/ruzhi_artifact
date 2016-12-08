package com.ruzhi.demo.test.self.validation;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
public class DemoForSelfValidation {

    @SelfNotEmpty(message = "company is blank")//java有自带的NotEmpty，这里只是实验一下自定义注解，实验的时候最好不要和自带的重名
    private String company="";

    @MyPatternOfString.List({
            @MyPatternOfString(mustContainLetter = "CH", message = "It does not belong toChina"),
            @MyPatternOfString(mustContainLetter = "MainLand", message = "It does not belong toMainLand")
    })
    private String place = "";

    @NotNull
    @Valid
    private Person person;  //级联验证的方式(首先需要保证对象不会空，否则在级联验证是会异常，可能不会输出验证信息)

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
