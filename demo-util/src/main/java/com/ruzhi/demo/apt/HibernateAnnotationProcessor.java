package com.ruzhi.demo.apt;

/**
 * Created by chunlong.wchl on 2016/1/16.
 * Description:
 */

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class HibernateAnnotationProcessor implements AnnotationProcessor {
    //Annotation处理器环境，是该处理器与APT交互的重要途径
    private AnnotationProcessorEnvironment env;

    //构造HibernateAnnotationProcessor对象时，获得处理器环境
    public HibernateAnnotationProcessor(AnnotationProcessorEnvironment env) {
        this.env = env;
    }

    //循环处理每个对象
    public void process() {
        //遍历每个class文件(TypeDeclaration是静态，只要有类文件就可以获得该对象，而Class是动态的，必须由虚拟机装载了指定类文件后才会产生)
        for (TypeDeclaration t : env.getSpecifiedTypeDeclarations()) {
            //定义一个文件输出流，用于生成额外的文件
            FileOutputStream fos = null;
            //获取正在处理的类名
            String clazzName = t.getSimpleName();
            //获取类定义前的Persistent Annotation
            Persistent per = t.getAnnotation(Persistent.class);
            //当per Annotation不为空时才继续处理
            if (per != null) {
                try {
                    //创建文件输出流
                    fos = new FileOutputStream(clazzName + ".hbm.xml");
                    PrintStream ps = new PrintStream(fos);
                    //执行输出
                    ps.println("<?xml version=\"1.0\"?>");
                    ps.println("<!DOCTYPE hibernate-mapping");
                    ps.println(" PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"");
                    ps.println(" \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">");
                    ps.println("<hibernate-mapping>");
                    ps.print(" <class name=\"" + t);
                    //输出per的table()的值
                    ps.println("\" table=\"" + per.table() + "\">");
                    for (FieldDeclaration f : t.getFields()) {
                        //获取指定FieldDeclaration前面的 IdProperty Annotation
                        IdProperty id = f.getAnnotation(IdProperty.class);

                        //如果id Annotation不为空
                        if (id != null) {
                            //执行输出
                            ps.println(" <id name=\"" + f.getSimpleName()
                                    + "\" column=\"" + id.column()
                                    + "\" type=\"" + id.type()
                                    + "\">");
                            ps.println(" <generator + id.generator() + \"/>");
                            ps.println(" </id>");
                        }

                        //获取指定FieldDeclaration前面的 Property Annotation
                        Property p = f.getAnnotation(Property.class);
                        //如果p Annotation不为空
                        if (p != null) {
                            //执行输出
                            ps.println(" <property name=\"" + f.getSimpleName()
                                    + "\" column=\"" + p.column()
                                    + "\" type=\"" + p.type()
                                    + "\"/>");
                        }
                    }
                    ps.println(" </class>");
                    ps.println("</hibernate-mapping>");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //关闭输出流
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
/**
 * TypeDeclaration又包含了如下三个常用方法来获得对应的程序元素。
 * <p/>
 * getFields:获取该类声明里的所有成员变量声明，返回值是集合元素FieldDeclaration的集合
 * getMethods:获取该类声明里的所有成员声明，返回值是集合元素MethodDeclaration的集合
 * getPackage:获取该类声明里的包声明，返回值是TypeDeclaration
 * 上面三个方法返回的TypeDeclaration,FieldDeclaration,MethodDeclaration都可调用getAnnotation方法来访问修饰它们的Annotation,上面程序中就是获取不同程序元素的Annotation的代码。
 *
 * 提供了上面的Annotation处理器类之后，还应该为该Annotation处理器提供一个处理工厂，处理工厂负责决定该处理器支持哪些Annotation，并通过getProcessorFor方法来生成一个Annotation处理哭对象
 提供了上面的处理器工厂后，就可以使用APT工具来处理上面的Person.java源文件，并根据该源文件来生成一个XML文件。 APT工具位于JDK的安装路径的bin路径下。。
==========================
 运行APT命令时，可以使用-factory选项来指定处理器工厂类
 如下所示
 rem 使用HibernateAnnotationFactory作为处理器工厂来处理Person.java中的Annotation
 apt -factory HibernateAnnotationFactory Person.java

 使用APT工具，HibernateAnnotationFactory工厂来处理Person.java后，将可以看到在相同路径下，生成了一个Person.hbm.xml文件了，
 该文件内容如下
 <?xml version="1.0"?>
 <!DOCTYPE hibernate-mapping
 PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
 "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
 <hibernate-mapping>
 <class name="Person" table="persons_table">
 <id name="id" column="person_id" type="integer">
 <generator </id>
 <property name="name" column="person_name" type="string"/>
 <property name="age" column="person_age" type="integer"/>
 </class>
 </hibernate-mapping>

 */
