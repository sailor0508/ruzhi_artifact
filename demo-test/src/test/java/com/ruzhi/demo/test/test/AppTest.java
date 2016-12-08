package com.ruzhi.demo.test.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest{

    public  static void main(String args[]){
        MySingleton a =  MySingleton.getInstance();
        MySingleton b =  MySingleton.getInstance();
        System.out.println(a);
        System.out.println(b);
    }


}
