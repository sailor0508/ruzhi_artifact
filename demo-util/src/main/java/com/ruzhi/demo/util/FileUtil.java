package com.ruzhi.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by chunlong.wchl on 2015/4/27.
 */
public class FileUtil {

    public static String File2String(String filePath, String encoding,String splitParameter) {
        StringBuffer sb = new StringBuffer();
        File myFile = new File(filePath);
        if (!myFile.exists()) {
            System.err.println("Can't Find file,please write the absolute path!");
        }
        try {
            FileInputStream is = new FileInputStream(myFile);
            InputStreamReader isr = new InputStreamReader(is, encoding);//不传encoding就默认
            BufferedReader in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                if (line.trim().length() == 0) {
                    continue;
                } else {
                    sb.append(line+splitParameter);
                }
            }
            in.close();
            is.close();
        } catch (Throwable e) {
            System.err.println("read file failed");
            e.printStackTrace();
        }
        return sb.toString();
    }
}
