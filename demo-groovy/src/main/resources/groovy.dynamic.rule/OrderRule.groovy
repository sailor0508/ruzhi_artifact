import com.ruzhi.demo.groovy.service.OrderService
import com.ruzhi.demo.groovy.service.RuleService
import com.ruzhi.demo.groovy.model.Order
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
		} catch (Exception e) {
			System.out.println("Param[ " + param + "] is not a int");
		}
	}
}
