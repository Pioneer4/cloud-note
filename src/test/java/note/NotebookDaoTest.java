package note;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import name.electricalqzhang.cloud.note.dao.NotebookDao;

public class NotebookDaoTest extends BaseTest {
	NotebookDao dao;
	
	@Before
	public void initDao() {
		dao = ctx.getBean("notebookDao", NotebookDao.class);
	}
	
	@Test
	public void testFindNotebookByUserId() {
		String userId="52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		List<Map<String, Object>> list = dao.findNotebooksByUserId(userId);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	@Test 
	public void testCountNotebookById() {
		String notebookId="0037215c-09fe-4eaa-aeb5-25a340c6b39b";
		int n = dao.countNotebookById(notebookId);
		System.out.println(n);
	}
}
