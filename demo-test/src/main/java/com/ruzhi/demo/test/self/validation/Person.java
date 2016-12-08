package com.ruzhi.demo.test.self.validation;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
public class Person {

    @SelfNotEmpty(message = "personName is blank")
    private String personName="";

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
