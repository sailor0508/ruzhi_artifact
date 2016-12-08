/*  不同的应用这里需要修改的
import me.joshua.demo4j.spring.groovy.dynamic.model.Order
import me.joshua.demo4j.spring.groovy.dynamic.service.OrderService
import me.joshua.demo4j.spring.groovy.dynamic.service.RuleService
*/

import com.ruzhi.demo.groovy.model.Order
import com.ruzhi.demo.groovy.service.OrderService
import com.ruzhi.demo.groovy.service.RuleService

import org.springframework.beans.factory.annotation.Autowired

class OrderRule implements RuleService {

	@Autowired
	private OrderService orderService;
	
	/**
	 * 参数为Order的ID，可用ID如下：
	 * <ol>
	 * <li>1</li>
	 * <li>2</li>
	 * </ol>
	 */
	public void run(String param) {
		try {
			int id = Integer.parseInt(param);
			Order order = orderService.findOrder(id);
			System.out.println(order);
			//System.out.println("123");
			System.out.println("555000");
		} catch (Exception e) {
			System.out.println("Param[ " + param + "] is not a int");
		}
	}
}
