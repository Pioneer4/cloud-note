package note;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import name.electricalqzhang.cloud.note.dao.NoteDao;
import name.electricalqzhang.cloud.note.dao.NotebookDao;
import name.electricalqzhang.cloud.note.entity.Note;

public class NoteDaoTest extends BaseTest {
	NoteDao dao;

	@Before
	public void initDao() {
		dao = ctx.getBean("noteDao", NoteDao.class);
	}

	@Test
	public void testFindNotesByNotebookId() {
		String notebookId = "fa8d3d9d-2de5-4cfe-845f-951041bcc461";
		List<Map<String, Object>> list = dao.findNotesByNotebookId(notebookId);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	@Test
	public void testFindNoteById() {
		String  noteId = "8d3763b2-8e01-48d4-a338-730b02ded9c9";
		Note note = dao.findByNoteId(noteId);
		System.out.println(note.getBody());
	}

	@Test
	public void testAddNote() {
		String id = "1";
	    String notebookId = "1";
	    String userId = "1";
	    String statusId = "1";
	    String typeId = "1";
	    String title = "test";
	    String body = "just for test!";
	    Long   createTime = null;
		Long   lastModifyTime = null;
		Note note = new Note(id, notebookId, userId, statusId, typeId, title, body, createTime, lastModifyTime);
		int n = dao.addNote(note);
		System.out.println(n);
	}

	@Test
	public void testUpdateNote() {
		Note note = new Note();
		note.setId("1");
		note.setTitle("张沁");
		note.setBody("modified");
		note.setLastModifyTime(System.currentTimeMillis());

		dao.updateNote(note);
		note = dao.findByNoteId("1");
		System.out.println(note);
	}
	
	@Test
	public void testDeleteNotes() {
		String id1 = "07305c91-d9fa-420d-af09-c3ff209608ff";
		String id2 = "5565bda4-ddee-4f87-844e-2ba83aa4925f";
		String id3 = "1ec185d6-554a-481b-b322-b562485bb8e8";
		int n = dao.deleteNotes(id1, id2, id3);
		System.out.println(n);
	}
}
