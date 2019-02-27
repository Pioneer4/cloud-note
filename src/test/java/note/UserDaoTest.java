package note;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import name.electricalqzhang.cloud.note.dao.UserDao;
import name.electricalqzhang.cloud.note.entity.User;

public class UserDaoTest extends BaseTest {
	UserDao dao;

	@Before
	public void initDao() {
		dao = ctx.getBean("userDao", UserDao.class);
	}

	@Test
	public void testFindByUserName() {
		String name = "demo";
		User user = dao.findUserByName(name);
		System.out.println(user);
	}

	@Test
	public void testAddUser() {
		String id = UUID.randomUUID().toString();
		String name = "Tony";
		String salt = "今天吃了吗？";
		String password = DigestUtils.md5Hex(salt+"123456");
		String token = "";
		String nick = "";
		User user = new User(id, name, password, token, nick);
		int n = dao.addUser(user);
		System.out.println(n);
		System.out.println(password);
	}

}
