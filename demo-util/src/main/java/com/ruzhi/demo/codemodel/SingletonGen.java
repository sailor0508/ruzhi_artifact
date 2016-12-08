package com.ruzhi.demo.codemodel;

/**
 * Created by chunlong.wchl on 2016/1/16.
 * Description: http://www.ibm.com/developerworks/cn/java/j-lo-codemodel/index.html
 * 如何实现单例类生成器
 * 我们来分析一下：要实现一个单例生成器，需要首先创建一个 Singleton 类，在该类中定义一个静态成员变量 instance。我们可以用代码生成该类的构造函数 Singleton()。Singleton 类还必须有一个静态的公共的方法来获取到实例，我们将其命名为 getInstance()，此外，我们给 Singleton 类创建一个名为 sayHello() 的成员方法，让其打印出传入的参数。
 * 为了测试我们的 Singleton 类，我们再让程序生成另外一个类 singletonTest，这个类将通过 Singleton 类的 getInstance() 方法得到一个单例实例，再调用 sayHello() 方法打印出“Hello CodeModel!”。
 * 有了以上的分析，结合之前介绍的 CodeModel 生成类、变量、方法以及控制语句的方法，代码就很容易写出来的。
 */

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDocComment;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import java.io.File;

/**
 * This class will generate a singleton class "Singleton.java" and a test class
 * "SingletonTest.java" under the specified package.
 *
 * @author Sonia (sxyu@cn.ibm.com)
 *
 */
public class SingletonGen {
    public void genSingleton() throws Exception {
        JCodeModel cm = new JCodeModel();
        JType type = cm.parseType("Singleton");
        File destDir = new File("D:/workSpace/springMavenDemo/demo-util/src/main/java/com/ruzhi/demo/codemodel");
        JDefinedClass dc = cm._class("com.ruzhi.demo.codemodel.dw.sample.Singleton");
        // 定义静态成员变量
        dc.field(JMod.PRIVATE + JMod.STATIC, type, "instance");
        // 定义单例类 Singleton 的构造函数
        dc.constructor(JMod.PRIVATE);

        // 生成 Singleton 类的成员方法 getInstanceMethod
        JMethod getInstanceMethod = dc.method(JMod.PUBLIC + JMod.STATIC, type, "getInstance");
        JBlock getInstanceBody = getInstanceMethod.body();
        JFieldRef fieldRef = JExpr.ref("instance");
        JConditional conditionIf = getInstanceBody._if(fieldRef.eq(JExpr._null()));
        JBlock thenPart = conditionIf._then();
        thenPart.assign(fieldRef, JExpr._new(type));
        getInstanceBody._return(fieldRef);

        // 生成 Singleton 类的成员方法 sayHelloMethod
        JMethod sayHelloMethod = dc.method(JMod.PUBLIC, cm.parseType("void"), "sayHello");

        // 生成方法级的 javadoc
        sayHelloMethod.javadoc().add("This method will say Hello to the name.");
        JBlock sayHelloBody = sayHelloMethod.body();
        sayHelloMethod.param(cm.parseType("String"), "name");
        JClass sys = cm.ref("java.lang.System");
        JFieldRef ot = sys.staticRef("out");
        JExpression sentance1 = JExpr.lit("Hello ").invoke("concat").arg(JExpr.ref("name"));
        JExpression sentance2 = sentance1.invoke("concat").arg("!");
        sayHelloBody.invoke(ot, "println").arg(sentance2);
        cm.build(destDir);
    }

    public void genTest() throws Exception {
        JCodeModel cm = new JCodeModel();
        File destDir = new File("D:/workSpace/springMavenDemo/demo-util/src/main/java/com/ruzhi/demo/codemodel");
        JDefinedClass dc = cm._class("com.ruzhi.demo.codemodel.dw.sample.singletonTest");
        // 生成类级的 javadoc
        JDocComment jdoc = dc.javadoc();
        jdoc.add("This is the class to test the Singleton class.");
        jdoc.addXdoclet("author Sonia (sxyu@cn.ibm.com)");
        // 生成 main 方法
        JMethod mainMethod = dc.method(JMod.PUBLIC + JMod.STATIC, cm.parseType("void"), "main");
        JBlock mainBody = mainMethod.body();
        mainMethod.param(cm.parseType("String[]"), "args");
        JClass singleton = cm.ref("Singleton");
        JInvocation getIns = singleton.staticInvoke("getInstance");
        mainBody.invoke(getIns, "sayHello").arg(JExpr.lit("CodeModel"));
        cm.build(destDir);
    }

    public static void main(String args[]) {
        SingletonGen sg = new SingletonGen();
        try {
            sg.genSingleton();
            sg.genTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 package dw.sample;
 public class Singleton {
 private static Singleton instance;
 private Singleton() { }
 public static Singleton getInstance() {
 if(instance== null) {
 instance= new Singleton();
 }
 return instance;
 }

 public void sayHello(String name) {
 System.out.println("Hello ".concat(name).concat("!"));
 }
 }
 =========================
 package dw.sample;
 public class SingletonTest {
 public static void main(String[] args) {
 Singleton.getInstance().sayHello("CodeModel");
 }
 }
 */