package com.ruzhi.demo.test.groovy;

import javax.script.*;
import java.util.Date;

/**
 * Created by chunlong.wchl on 2015/6/3.
 * http://blog.csdn.net/haigenwong/article/details/22947173
 */

/**
 * Java中运行Groovy,有三种比较常用的类支持:GroovyShell,GroovyClassLoader以及Java-Script引擎(JSR-223).
 1) GroovyShell: 通常用来运行"script片段"或者一些零散的表达式(Expression)
 2) GroovyClassLoader: 如果脚本是一个完整的文件,特别是有API类型的时候,比如有类似于JAVA的接口,面向对象设计时,通常使用GroovyClassLoader.
 3) ScriptEngine: JSR-223应该是推荐的一种使用策略.规范化,而且简便.
 */
public class GroovyScriptDemo {
    public static void main(String args[]) throws ScriptException, NoSuchMethodException {

        ScriptEngineManager factory = new ScriptEngineManager();
        //每次生成一个engine实例
        ScriptEngine engine = factory.getEngineByName("groovy");
        System.out.println(engine.toString());
        assert engine != null;
        //javax.script.Bindings
        Bindings binding = engine.createBindings();
        binding.put("date", new Date());
        //如果script文本来自文件,请首先获取文件内容
        engine.eval("def getTime(){return date.getTime();}", binding);
        engine.eval("def sayHello(name,age){return 'Hello,I am ' + name + ',age' + age;}");
        Long time = (Long) ((Invocable) engine).invokeFunction("getTime", null);
        System.out.println(time);
        String message = (String) ((Invocable) engine).invokeFunction("sayHello", "zhangsan", new Integer(12));
        System.out.println(message);
    }
}