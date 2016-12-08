package com.ruzhi.demo.test.all.one;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MyProcessor extends AbstractProcessor {//这个类被称为抽象处理器类，每一个处理器都是从这个类继承

    private Types typeUtils;   //一个用来处理TypeMirror的工具类
    private Elements elementUtils;  //一个用来处理Element的工具类
    private Filer filer;  //这个工具可以支持向当前工程输出新的Java代码
    private Messager messager; //可以让Javac编译器输出错误提示


    @Override
    public synchronized void init(ProcessingEnvironment env) {//这个方法在整个处理器被初始化的时候被调用。
        System.err.println("MyProcessor Run");
        super.init(env);
        elementUtils = env.getElementUtils();
        filer = env.getFiler();
        typeUtils = env.getTypeUtils();
        messager = env.getMessager();
    }

    /**
     * 在每一趟处理的时候被调用，由于我们处理器是一个递归处理的过程，新产生的代码，也可能保护能够被当前处理器处理的注解，所以采取的是多趟处理的方案
     */
    @Override
    public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) {
        /*System.err.println("MyProcessor Process");
        Map<String, MyAnnotatedClass> classmap = new HashMap<String, MyAnnotatedClass>();

        Set<? extends Element> elementSet = env.getElementsAnnotatedWith(MyAnnotation.class);
        // 获取可执行节点（函数）的方法，遍历所有标记了注解的语法元素
        for (Element e : elementSet) {
            if (e.getKind()!= ElementKind.METHOD) {
                error(e,"错误的注解类型，只有函数能够被该 @%s 注解处理", MyAnnotation.class.getSimpleName());
                return true;
            }

            ExecutableElement element = (ExecutableElement) e;
            // 将解析后的语法元素放置到自定义的数据结构中
            MyAnnotatedMethod mymethod = new MyAnnotatedMethod(element);
            String classname = mymethod.getSimpleClassName();

            // 将解析出的Class进行分类，同一类下的函数都生成一个接口
            MyAnnotatedClass myclass = classmap.get(classname);
            if (myclass == null) {
                PackageElement pkg = elementUtils.getPackageOf(element);
                myclass = new MyAnnotatedClass(pkg.getQualifiedName().toString(), classname);
                myclass.addMethod(mymethod);
                classmap.put(classname,myclass);
            } else
                myclass.addMethod(mymethod);

        }
        // 代码生成
        for (MyAnnotatedClass myclass : classmap.values()) {
            myclass.generateCode(elementUtils, filer);
        }*/
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {//返回能处理的注解的全名
        Set<String> strings = new TreeSet<String>();
        strings.add("com.example.MyAnnotation");
        return strings;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {//返回能支持的代码版本
        return SourceVersion.latestSupported();
    }
    private void error(Element e, String msg, Object... args) {
        messager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }
}
