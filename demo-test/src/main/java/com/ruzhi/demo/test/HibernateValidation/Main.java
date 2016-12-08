package com.ruzhi.demo.test.HibernateValidation;

/**
 * Created by chunlong.wchl on 2015/6/2.
 */
public class Main {
    public static void main(String args[]){
        DemoForHibernateValidation request = new DemoForHibernateValidation();
        System.out.println(MyValidatorUtil.validateModel(request));
    }
}
