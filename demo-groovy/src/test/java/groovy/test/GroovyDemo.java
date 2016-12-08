package groovy.test;

import com.ruzhi.demo.groovy.service.RuleEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * http://my.oschina.net/joshuazhan/blog/144830
 */
public class GroovyDemo {

	private static RuleEngine ruleEngine;
	private static BufferedReader reader;

	public static void setUpClass()  {
		reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:demo-groovy.xml");
			ruleEngine = (RuleEngine) context.getBean("ruleEngine");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		setUpClass();
		try {
			while (true) {
				// 循环获取输入，模拟触发规则运行的事件
				String rule = getInput("Rule: ");
				if ("exit".equals(rule)) {
					System.out.println("start to exit....");
					break;
				}
				String param = getInput("Param: ");
				ruleEngine.run(rule, param);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("already exit....");
	}

	/**
	 * 提示用户输入，并返回输入内容
	 * 
	 * @param prompt
	 * @return
	 */
	private static String getInput(String prompt) {
		System.out.print(prompt);
		String input = null;
		try {
			input = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return input;
	}
}
