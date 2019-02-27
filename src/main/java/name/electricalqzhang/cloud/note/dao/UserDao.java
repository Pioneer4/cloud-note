package name.electricalqzhang.cloud.note.dao;

import name.electricalqzhang.cloud.note.entity.User;

public interface UserDao {
	User findUserByName(String name);
	int addUser(User user);
	User findUserById(String userId);
}
