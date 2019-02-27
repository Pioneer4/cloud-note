package note;

import org.junit.Before;
import org.junit.Test;

import name.electricalqzhang.cloud.note.dao.PersonDao;
import name.electricalqzhang.cloud.note.entity.Person;

public class PersonDaoTest extends BaseTest {
	PersonDao dao;
	
	@Before
	public void initDao() {
		dao = ctx.getBean("personDao", PersonDao.class);
	}
	
	@Test
	public void testAddPerson() {
		Person person = new Person(null, "Tom");
		int n = dao.addPerson(person);
		System.out.println(n);
		System.out.println(person);
	}
	
}
