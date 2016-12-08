package groovy.test;

import com.ruzhi.demo.groovy.service.RuleEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:demo-groovy.xml")
public class Demo {

	@Autowired
	private RuleEngine ruleEngine;

	private BufferedReader reader;

	{
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * 这里的单元测试是行不通的，因为控制台不能输入，控制台输入成功需要public static void main 类型的方法
	 */
	@Test
	public void run() {
		try {
			while (true) {
				// 循环获取输入，模拟触发规则运行的事件
				String rule = getInput("Rule: ");
				if ("exit".equals(rule)) {
					break;
				}
				String param = getInput("Param: ");
				ruleEngine.run(rule, param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提示用户输入，并返回输入内容
	 * 
	 * @param prompt
	 * @return
	 */
	private String getInput(String prompt) {
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
