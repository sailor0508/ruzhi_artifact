package com.ruzhi.demo.groovy.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 规则引擎的实现示例
 * 
 */
@Service
public class RuleEngine implements ApplicationContextAware {

	private ApplicationContext parentContext;
	private ClassPathXmlApplicationContext ruleContext;

	// 规则配置的资源文件
	@Value("${res.resource}/rules.xml")
	private Resource ruleConfig;
	private long lastModified;

	/**
	 * 初始化方法，记录初始配置的时间并加载规则，通过注解标记为init方法。
	 */
	@PostConstruct
	public void init() {
		try {
			lastModified = ruleConfig.lastModified();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		reload();
		System.out.println("Rule engine initialized.");
	}

	/**
	 * 运行指定规则
	 * 
	 * @param ruleName
	 *            规则名字
	 * @param param
	 *            规则参数
	 */
	public void run(String ruleName, String param) {
		// 查找规则
		if (!ruleContext.containsBean(ruleName)) {
			System.out.println("Rule[" + ruleName + "] not found.");
			return;
		}

		// 如果规则存在，运行规则
		RuleService service = ruleContext.getBean(ruleName, RuleService.class);
		if (null != service) {
			try {
				service.run(param);
			} catch (Exception e) {
				System.out.println("Error occur while runing the Rule[" + ruleName + "]");
			}
		}
	}

	/**
	 * 以固定的时间间隔检查规则的配置的变更，如有变更则进行配置的重加载。
	 */
	@Scheduled(fixedDelay = 5000)
	public void checkUpdate() {
		try {
			// 比对规则变更时间，有变动时进行重新加载
			long currentLastModified = ruleConfig.lastModified();
			if (this.lastModified < currentLastModified) {
				reload();
				this.lastModified = currentLastModified;
				System.out.println("\nRule engine updated.");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 重新装载规则引擎，创建新的规则Context，并销毁旧Context。
	 */
	private synchronized void reload() {
		if (!ruleConfig.exists()) {
			throw new RuntimeException("Rule config not exist.");
		}
		ClassPathXmlApplicationContext oldContext = this.ruleContext;

		try {
			String[] config = { ruleConfig.getURI().toString() };
			ClassPathXmlApplicationContext newContext = new ClassPathXmlApplicationContext(config, parentContext);
			this.ruleContext = newContext;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// 销毁旧的规则Context
		if (null != oldContext && oldContext.isActive()) {
			oldContext.close();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.parentContext = applicationContext;
	}

}
