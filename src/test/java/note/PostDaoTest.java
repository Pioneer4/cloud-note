package note;

import org.junit.Before;
import org.junit.Test;

import name.electricalqzhang.cloud.note.dao.PostDao;
import name.electricalqzhang.cloud.note.entity.Post;

public class PostDaoTest extends BaseTest {
	PostDao dao;
	
	@Before
	public void initDao() {
		dao = ctx.getBean("postDao", PostDao.class);
	}
	
	@Test
	public void testFindPostById() {
		Post post = dao.findPostById(1);
		System.out.println(post);
	}
}
