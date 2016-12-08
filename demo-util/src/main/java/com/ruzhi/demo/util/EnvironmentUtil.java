package com.ruzhi.demo.util;

import java.util.Properties;

/**
 * Created by chunlong.wchl on 2015/5/12.
 */
public class EnvironmentUtil {

    public static boolean isWinOs(){
        Properties prop = System.getProperties();

        String os = prop.getProperty("os.name");
        return  os.startsWith("win") || os.startsWith("Win");
    }

    public  static void  main(String args[]){
        System.out.print(isWinOs());
    }
}
