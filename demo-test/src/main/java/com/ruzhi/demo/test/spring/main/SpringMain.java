package com.ruzhi.demo.test.spring.main;

import com.ruzhi.demo.test.spring.aspect.MyLoggable;
import com.ruzhi.demo.test.spring.service.EmployeeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:demo-test.spring.xml");
        EmployeeService employeeService = ctx.getBean("employeeService", EmployeeService.class);
        employeeService.power(2, 3);
        System.out.println("====================================================");
        System.out.println(employeeService.getEmployee().getName());
        System.out.println("====================================================");
        employeeService.getEmployee().setName("Pankaj");

        employeeService.getEmployee().throwException();

        ctx.close();
    }
    @MyLoggable
    public  static  void test(){
    int i = 0;
    }

}
/**
 * http://www.importnew.com/13367.html
 * Aspect：切面，实现企业应用中多个类的共同关注点，例如事务管理。切面可以是使用 Spring XML 配置的一个普通类或者是我们使用 Spring AspectJ 定义的一个标有 @Aspect 注解的类。
 * Join Point：连接点，一个连接点是应用程序中的一个特定的点，例如方法执行、异常处理、改变对象变量的值等等。在 Spring AOP 中，一个连接点永远是指一个方法的执行。
 * Advice：通知，通知是指在一个特别的连接点上发生的动作。在编程中，他们是当一个连接点匹配到一个切入点时执行的方法。你可以把通知想成 Strust2 中的拦截器或者是 Servlet 中的过滤器。
 * Pointcut：切入点，切入点是一些表达式，当匹配到连接点时决定通知是否需要执行。切入点使用不同种类型的表达式来匹配连接点，Spring 框架使用 AspectJ 表达式语法。
 * Target Object：通知执行的目标对象。Spring AOP 是使用运行时的代理来实现的，所以该对象永远是一个代理对象。这意味着一个子类在运行期间会被创建，该类的目标方法会被覆盖并且通知会基于他们的配置被引入。
 * AOP proxy：Spring AOP 实现使用 JDK 的动态代理来创建包含有目标类和通知调用的代理类，这些类被称为 AOP 代理类。我们也可以使用 CGLIB 代理来作为 Spring AOP 项目的依赖实现。
 * Weaving：织入，将切面和其他用于创建被通知的代理对象的类联系起来的过程。这可以是在运行时完成，也可以是在代码加载过程中或者运行时。Spring AOP 是在运行时完成织入的过程。
 * <p/>
 * Before Advice：在连接点方法执行之前运行。我们可以使用 @Before 注解来标记一个通知类型为 Before Advice。
 * After (finally) Advice：在连接点方法执行完成之后运行。我们可以使用 @After 来创建一个 After (finally) Advice。
 * After Returning Advice：在方法返回之后运行，通过 @AfterReturning 注解创建。
 * After Throwing Advice：在方法抛出异常之后运行，通过 @AfterThrowing 注解创建。
 * Around Advice：这是最重要和最强的通知。这个通知在连接点方法前后运行并且我们可以决定该通知是否运行，通过 @Around 注解创建。
 */

/**
 * Before Advice：在连接点方法执行之前运行。我们可以使用 @Before 注解来标记一个通知类型为 Before Advice。
 After (finally) Advice：在连接点方法执行完成之后运行。我们可以使用 @After 来创建一个 After (finally) Advice。
 After Returning Advice：在方法返回之后运行，通过 @AfterReturning 注解创建。
 After Throwing Advice：在方法抛出异常之后运行，通过 @AfterThrowing 注解创建。
 Around Advice：这是最重要和最强的通知。这个通知在连接点方法前后运行并且我们可以决定该通知是否运行，通过 @Around 注解创建。
 * */