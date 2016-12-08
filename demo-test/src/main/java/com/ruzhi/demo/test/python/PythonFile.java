package com.ruzhi.demo.test.python;

import com.ruzhi.demo.util.EnvironmentUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
public class PythonFile {

    public static void main(String args[]){
        excutePythonScript("");
    }
    private static String excutePythonScript(String parameter) {
        String url = "";
        try {
            String filePath = "./demo-test/src/main/java/com/ruzhi/demo/test/python/HelloWorld.py";
            if(EnvironmentUtil.isWinOs()){
                filePath    = "./demo-test/src/main/java/com/ruzhi/demo/test/python/HelloWorld.py";
            }

           // Process pr = Runtime.getRuntime().exec("python "+filePath + parameter);
            Process pr = Runtime.getRuntime().exec("python "+filePath);//文件不存在或语法不正确，这里不会报错，所以需要测试后再在这里调用
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                url = line;
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return url;
    }
}
