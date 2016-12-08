package com.ruzhi.demo.test.all;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 注意:注解不支持继承例如 extends @xxx。
 * 注解的default默认值不可以为null
 * 使用注解可以减少对xml等外部文件的依赖，使得对类的定义可以在一处实现，避免了一个类两处定义的麻烦。spring和hibernate就采用的这样的方法。
 */
public class Test {

    public static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allownull()) {
            constraints += " NOT NULL";
        }
        if (con.primarykey()) {
            constraints += " PRIMARY KEY";
        }
        return constraints;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner s = new Scanner(System.in);
        String name = s.next();                                    //从控制台输入一个类名，我们输入com.ruzhi.demo.test.all.User即可
        Class<?> cl = Class.forName(name);                         //加载类，如果该类不在默认路径底下，会报 java.lang.ClassNotFoundException
        DBTable dbTable = cl.getAnnotation(DBTable.class);         //从User类中获取DBTable注解
        if (dbTable == null) {                                       //如果没有DBTable注解，则直接返回，我们写了，当然有
            return;
        }
        String tableName = (dbTable.name().length() < 1) ? cl.getName() : dbTable.name();//获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字
        List<String> columnDefs = new ArrayList<String>();
        for (Field field : cl.getDeclaredFields()) {                  //获取声明的属性
            String columnName = null;
            Annotation[] anns = field.getDeclaredAnnotations();//获取注解，一个属性可以有多个注解，所以是数组类型
            if (anns.length < 1) {
                continue;
            }
            if (anns[0] instanceof SQLInteger){                //判断注解类型
                SQLInteger sInt = (SQLInteger) anns[0];
                columnName = (sInt.name().length() < 1) ? field.getName() : sInt.name();//获取列名称与获取表名一样
                columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));//使用一个方法，自己写的getConstraints(Constraints constraints)获取列定义
            }

            if (anns[0] instanceof SQLString) {
                SQLString sStr = (SQLString) anns[0];
                columnName = (sStr.name().length() < 1) ? field.getName().toUpperCase() : sStr.name();
                columnDefs.add(columnName + " VARCHAR(" + sStr.value() + ")" + getConstraints(sStr.constraints()));
            }
        }
        StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
        for (String columnDef : columnDefs) {
            createCommand.append("\n    " + columnDef + ",");
        }
        String tableCreate = createCommand.substring(0, createCommand.length() - 1) + "\n);";
        System.out.println(tableCreate);                        //打印出来
    }

}
