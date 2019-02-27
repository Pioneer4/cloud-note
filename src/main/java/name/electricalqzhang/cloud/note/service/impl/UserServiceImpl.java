package name.electricalqzhang.cloud.note.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import name.electricalqzhang.cloud.note.dao.UserDao;
import name.electricalqzhang.cloud.note.entity.User;
import name.electricalqzhang.cloud.note.service.PasswordException;
import name.electricalqzhang.cloud.note.service.UserNameException;
import name.electricalqzhang.cloud.note.service.UserNotFoundException;
import name.electricalqzhang.cloud.note.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Value("#{jdbc.salt}")
	private String salt;
	
	@Resource
	private UserDao userDao;

	public User login(String name, String password) 
			throws UserNotFoundException, PasswordException {
		
		if (password == null || password.trim().isEmpty()) {
			throw new PasswordException("密码空");
		}
		if (name == null || name.trim().isEmpty()) {
			throw new UserNotFoundException("用户名空");
		}
		
		User user = userDao.findUserByName(name);
		if (user == null) {
			throw new UserNotFoundException("用户名错误");
		}
		
		String pwd = DigestUtils.md5Hex(salt + password);
		
		if (pwd.equals(user.getPassword())) {
			return user;
		}
		throw new PasswordException("密码错误");
	}

	public User regist(String name, String nick, String password, String confirm)
			throws UserNameException, PasswordException {
		/* 检查name，不能重复 */
		if (name==null || name.trim().isEmpty()) {
			throw new UserNameException("不能为空");
		}
		User one = userDao.findUserByName(name);
		if (one != null) {
			throw new UserNameException("已注册");
		}
		
		/* 检查password，需要一致 */
		if (password==null || password.trim().isEmpty()) {
			throw new PasswordException("不能空");
		}
		if (!password.equals(confirm)) {
			throw new PasswordException("密码不一致");
		}
		
		/* 检查nick，为空，默认使用name */
		if (nick==null || nick.trim().isEmpty()) {
			nick = name;
		}
		
		String id = UUID.randomUUID().toString();
		String token = "";		
		//密码加密
		password = DigestUtils.md5Hex(salt+password.trim());
		User user = new User(id, name, password, token, nick);
		int n = userDao.addUser(user);
		if (n != 1) {
			throw new RuntimeException("用户添加失败");
		}
		return user;
	}
}
