
import com.ruzhi.demo.groovy.service.MemberService
import com.ruzhi.demo.groovy.service.RuleService
import com.ruzhi.demo.groovy.model.Member
import org.springframework.beans.factory.annotation.Autowired

class MemberRule implements RuleService {

	@Autowired
	private MemberService memberService

	/**
	 * 参数为会员邮箱，可用邮箱如下：
	 * <ol>
	 * <li>daonan.zhan@gmail.com</li>
	 * <li>zdn880729@gmail.com</li>
	 * </ol>
	 */
	public void run(String param) {
		Member member = memberService.findMember(param);
		System.out.println(member);
	}
}
