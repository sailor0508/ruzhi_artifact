package com.ruzhi.demo.test.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class EmployeeAroundAspect {
	private static org.slf4j.Logger log = LoggerFactory.getLogger(EmployeeAroundAspect.class);

	@Around("execution(* com.ruzhi.demo.test.spring.model.Employee.getName())")
	public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("EmployeeAroundAspect: employeeAroundAdvice()  Before invoking getName() method");
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("EmployeeAroundAspect: employeeAroundAdvice()  After invoking getName() method. Return value="+value);
		return value;
	}

	@Around("execution(* *(..)) && @annotation(com.ruzhi.demo.test.spring.aspect.MyLoggable)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = point.proceed();
		String fethodName = MethodSignature.class.cast(point.getSignature()).getMethod().getName();
		String parametor = Arrays.toString(point.getArgs());
		String outStr = String.format("#%s(%s): %s in %s ms",
				fethodName,
				parametor,
				result.toString(),
				(System.currentTimeMillis() - start));
		System.out.println(outStr);
		StringBuffer sb = new StringBuffer();
		sb.append("fethodName:").append(fethodName);
		sb.append("parametor:").append(parametor);
		sb.append("result:").append(result.toString());
		sb.append("time:").append(System.currentTimeMillis() - start);
		log.info(sb.toString());

		return result;
	}
}
