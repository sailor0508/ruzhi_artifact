package com.ruzhi.demo.test.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class EmployeeAspectPointcut {

	@Before("getNamePointcut()")
	public void loggingAdvice(){
		System.out.println("EmployeeAspectPointcut: Executing loggingAdvice() on getName()");
	}
	
	@Before("getNamePointcut()")
	public void secondAdvice(){
		System.out.println("EmployeeAspectPointcut: Executing secondAdvice() on getName()");
	}
	
	@Pointcut("execution(public String getName())")
	public void getNamePointcut(){}
	
	@Before("allMethodsPointcut()")
	public void allServiceMethodsAdvice(){
		System.out.println("EmployeeAspectPointcut: Before executing allServiceMethodsAdvice() on  allMethodsPointcut()");
	}
	
	//Pointcut to execute on all the methods of classes in a package
	@Pointcut("within(com.ruzhi.demo.test.spring.service.*)")
	public void allMethodsPointcut(){
		//pointcut 方法体应该为空，所以即使这里有输出语句也不会执行
		System.out.println("EmployeeAspectPointcut: Before executing allMethodsPointcut() on  service.*.*()");
	}
	
}
