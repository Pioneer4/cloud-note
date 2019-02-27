package note;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import name.electricalqzhang.cloud.note.dao.UserDao;
import name.electricalqzhang.cloud.note.entity.User;
import name.electricalqzhang.cloud.note.service.NotebookService;

public class NotebookServiceTest extends BaseTest {
	
	private NotebookService notebookService;
	
	@Before
	public void initUserService() {
		notebookService = ctx.getBean("notebookService", NotebookService.class);
	}
	
	@Test
	public void testListNotebooks() {
		String userId="52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		List<Map<String, Object>> list = notebookService.listNotebooks(userId);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	@Test
	public void listNotebooks() {
		String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		List<Map<String, Object>> list = notebookService.listNotebooks(userId, 0);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
}
