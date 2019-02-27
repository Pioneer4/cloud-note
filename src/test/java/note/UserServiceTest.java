package note;

import org.junit.Before;
import org.junit.Test;

import name.electricalqzhang.cloud.note.dao.UserDao;
import name.electricalqzhang.cloud.note.entity.User;
import name.electricalqzhang.cloud.note.service.UserService;

public class UserServiceTest extends BaseTest {
	private UserService userService;
	
	@Before
	public void initUserService() {
		userService = ctx.getBean("userService", UserService.class);
	}
	
	@Test
	public void testLogin() {
		String name = "demo";
		String password = "123456";
		User user = userService.login(name, password);
		System.out.println(user);
	}
	
	@Test
	public void testRegist() {
		User user = userService.regist("Andi", "Andi_nick", "123456", "123456");
		System.out.println(user);
	}
	
	
}
