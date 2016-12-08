package com.ruzhi.demo.test.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class EmployeeAnnotationAspect {

	@Before("@annotation(com.ruzhi.demo.test.spring.aspect.MyLoggable)")
	public void myAdvice(){
		System.out.println("EmployeeAnnotationAspect: Executing myAdvice!! on Loggable()");
	}
}
