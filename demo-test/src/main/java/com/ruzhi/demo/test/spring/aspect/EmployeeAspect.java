package com.ruzhi.demo.test.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class EmployeeAspect {

	@Before("execution(public String getName())")
	public void getNameAdvice(){
		System.out.println("EmployeeAspect: Executing getNameAdvice on getName()");
	}
	
	@Before("execution(* com.ruzhi.demo.test.spring.service.*.get*())")
	public void getAllAdvice(){
		System.out.println("EmployeeAspect: getAllAdvice on *.get*()");
	}
}
