package com.ruzhi.demo.core.test;

import java.util.Date;

/**
 * Created by chunlong.wchl on 2015/5/30.
 */
public class AuctionTest {
    public  static void main(String args[]){
        Auction a = new Auction();
        a.setName("name_name");
        a.setPrice("300");
        a.setWeight(40);
        a.setGmtCreate(new Date());
        a.setGmtModified(new Date());

        System.out.println(a.toString());

    }

}
