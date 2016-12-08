package com.ruzhi.demo.test.spring.service;


import com.ruzhi.demo.test.spring.aspect.MyLoggable;
import com.ruzhi.demo.test.spring.model.Employee;

public class EmployeeService {

	private Employee employee;
	
	public Employee getEmployee(){
		return this.employee;
	}
	
	public void setEmployee(Employee e){
		this.employee=e;
	}

	@MyLoggable
	public double power(int x, int p) {
		return Math.pow(x, p);
	}
}
