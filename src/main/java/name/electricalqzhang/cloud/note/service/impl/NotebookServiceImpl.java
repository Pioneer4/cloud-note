package name.electricalqzhang.cloud.note.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import name.electricalqzhang.cloud.note.dao.NotebookDao;
import name.electricalqzhang.cloud.note.dao.UserDao;
import name.electricalqzhang.cloud.note.entity.User;
import name.electricalqzhang.cloud.note.service.NotebookService;
import name.electricalqzhang.cloud.note.service.UserNotFoundException;

@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {

	@Resource
	private NotebookDao notebookDao;

	@Resource
	private UserDao userDao;

	@Value("#{jdbc.pageSize}")
	private int pageSize;

	public List<Map<String, Object>> listNotebooks(String userId)
			throws UserNotFoundException {
		if (userId==null && userId.trim().isEmpty()) {
			throw new UserNotFoundException("ID不存在");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		return notebookDao.findNotebooksByUserId(userId);
	}

	public List<Map<String, Object>> listNotebooks(String userId, Integer page)
			throws UserNotFoundException {
		if (userId==null && userId.trim().isEmpty()) {
			throw new UserNotFoundException("ID不存在");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		if (page == null) {
			page = 0;
		}

		int start = page * pageSize;
		String table = "cn_notebook";
		return notebookDao.findNotebooksByPage(userId, start, pageSize, table);
	}

}
