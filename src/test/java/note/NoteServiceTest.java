package note;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import name.electricalqzhang.cloud.note.dao.UserDao;
import name.electricalqzhang.cloud.note.entity.Note;
import name.electricalqzhang.cloud.note.entity.User;
import name.electricalqzhang.cloud.note.service.NoteService;

public class NoteServiceTest extends BaseTest {

	private NoteService noteService;

	@Before
	public void initNoteService() {
		noteService = ctx.getBean("noteService", NoteService.class);
	}

	@Test
	public void testListNotes() {
		String notebookId = "fa8d3d9d-2de5-4cfe-845f-951041bcc461";
		List<Map<String, Object>> list = noteService.listNotes(notebookId);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	@Test
	public void testGetNote() {
		String  noteId = "8d3763b2-8e01-48d4-a338-730b02ded9c9";
		Note note = noteService.getNote(noteId);
		System.out.println(note.getBody());
	}

	@Test
	public void testAddNote() {
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		String notebookId = "c8d81ee5-f8cd-49e8-b2e6-ab174a926d95";
		String title = "testAddNoteService";
		noteService.addNote(userId, notebookId, title);
		System.out.println("Add note OK !");
	}
	
	@Test
	public void testUpdateNote() {
		String noteId = "1";
		String title = "Hadoop";
		String body = "spark";
		boolean flag = noteService.updateNote(noteId, title, body);
		System.out.println(flag);
	}

	@Test
	public void testDeleteNodes() {
		String id1 = "3febebb3-a1b7-45ac-83ba-50cdb41e5fc1";
		String id2 = "9187ffd3-4c1e-4768-9f2f-c600e835b823";
		String id3 = "ebd65da6-3f90-45f9-b045-782928a5e2c0";
		String id4 = "fed920a0-573c-46c8-ae4e-368397846efd";  //fed920a0-573c-46c8-ae4e-368397846efd
		int n = noteService.deleteNoteById(id1, id2, id3, id4);
		System.out.println(n);
	}
	
	@Test
	public void testAddStars() {
		String userId = "03590914-a934-4da9-ba4d-b41799f917d1";
		boolean b = noteService.addStars(userId, 5);
		System.out.println(b);
		b = noteService.addStars(userId, 6);
		System.out.println(b);
	}
}
