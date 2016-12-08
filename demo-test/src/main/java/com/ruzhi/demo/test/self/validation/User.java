package com.ruzhi.demo.test.self.validation;

import javax.validation.groups.Default;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
public class User {

    @SelfNotEmpty (message = "firstname may be empty")
    private String firstname;

    @SelfNotEmpty(message = "middlename may be empty", groups = Default.class)
    private String middlename;

    @SelfNotEmpty(message = "lastname may be empty",groups = GroupA.class)
    private String lastname;

    @SelfNotEmpty(message = "country may be empty",groups = GroupB.class)
    private String country;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}


