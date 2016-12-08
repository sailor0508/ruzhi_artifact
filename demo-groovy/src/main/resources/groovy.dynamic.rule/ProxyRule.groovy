import com.ruzhi.demo.groovy.service.RuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

/**
 * 规则的代理，把调用委派给实际的规则实现来执行。<br>
 * 主要用于展示
 * <ol>
 *     <li>新加的规则可以立即被现有服务使用
 *     <li>服务可以被其他服务引用，组成复合的服务，实现复杂的业务场景
 * </ol>
 * 
 * @author Joshua
 */
class ProxyRule implements RuleService {

	/*
	 * 通过修改@Qualifier注解的配置可以替换后端的规则实现
	 */
	@Autowired
	@Qualifier("member")
	private RuleService backendRule;

	public void run(String param) {
		System.out.println("Invoke backend rule service");
		backendRule.run(param);
	}
}
