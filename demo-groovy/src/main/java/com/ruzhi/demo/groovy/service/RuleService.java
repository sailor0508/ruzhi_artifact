package com.ruzhi.demo.groovy.service;

/**
 * 规则接口，定义规则的运行方式
 * 
 */
public interface RuleService {

	/**
	 * 运行规则
	 * 
	 * @param param
	 *            规则所需的参数
	 */
	void run(String param);
}
