package com.ruzhi.demo.test.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
public class GroovyFile {

    public static void main(String args[]) throws Exception {
        parse();
    }

    /**
     * 解析groovy文件
     */
    public static void parse() throws Exception{
        GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        File sourceFile = new File("./demo-test/src/main/java/com/ruzhi/demo/test/groovy/HelloWorld.groovy");
        Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
        GroovyObject instance = (GroovyObject)testGroovyClass.newInstance();//proxy
        Long time = (Long)instance.invokeMethod("getTime", new Date());
        System.out.println(time);
        Date date = (Date)instance.invokeMethod("getDate", time);
        System.out.println(date);

    }
    /**
     * 加载已经编译的groovy文件(.class)
     */
    public static void load() throws Exception {
        GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\TestGroovy.class"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for(;;){
            int i = bis.read();
            if( i == -1){
                break;
            }
            bos.write(i);
        }
        Class testGroovyClass = classLoader.defineClass(null, bos.toByteArray());
        //instance of proxy-class
        //if interface API is in the classpath,you can do such as:
        //MyObject instance = (MyObject)testGroovyClass.newInstance()
        GroovyObject instance = (GroovyObject)testGroovyClass.newInstance();
        Long time = (Long)instance.invokeMethod("getTime", new Date());
        System.out.println(time);
        Date date = (Date)instance.invokeMethod("getDate", time);
        System.out.println(date.getTime());

        //here
        bis.close();
        bos.close();
        instance = null;
        testGroovyClass = null;
    }

    /*public static  void main(String args[]){

    }*/
}
