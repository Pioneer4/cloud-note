package name.electricalqzhang.cloud.note.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import name.electricalqzhang.cloud.note.dao.NoteDao;
import name.electricalqzhang.cloud.note.dao.NotebookDao;
import name.electricalqzhang.cloud.note.dao.StarsDao;
import name.electricalqzhang.cloud.note.dao.UserDao;
import name.electricalqzhang.cloud.note.entity.Note;
import name.electricalqzhang.cloud.note.entity.Stars;
import name.electricalqzhang.cloud.note.entity.User;
import name.electricalqzhang.cloud.note.service.NoteNotFoundException;
import name.electricalqzhang.cloud.note.service.NoteService;
import name.electricalqzhang.cloud.note.service.NotebookNotFoundException;
import name.electricalqzhang.cloud.note.service.NotebookNoteFoundException;
import name.electricalqzhang.cloud.note.service.UserNotFoundException;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Resource
	private UserDao userDao;

	@Resource
	private NoteDao noteDao;

	@Resource
	private NotebookDao notebookDao;

	@Resource
	private StarsDao starsDao;

	public List<Map<String, Object>> listNotes(String notebookId)
			throws NotebookNoteFoundException {
		if (notebookId==null || notebookId.trim().isEmpty()) {
			throw new NotebookNoteFoundException("ID为空");
		}

		int  n = notebookDao.countNotebookById(notebookId);
		if (n != 1) {
			throw new NotebookNoteFoundException("没有笔记本");
		}

		return noteDao.findNotesByNotebookId(notebookId);
	}
	
	@Transactional(readOnly=true)
	public Note getNote(String noteId)
			throws NoteNotFoundException {
		if (noteId==null || noteId.trim().isEmpty()) {
			throw new NoteNotFoundException("ID空");
		}
		Note note = noteDao.findByNoteId(noteId);
		if (note == null) {
			throw new NoteNotFoundException("ID错误");
		}

		return note;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Note addNote(String userId, String notebookId, String title)
			throws UserNotFoundException, NotebookNotFoundException {
		if (userId==null || userId.trim().isEmpty()) {
		   throw new UserNotFoundException("用户ID空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
		   throw new UserNotFoundException("该用户不存在");
		}
		if (notebookId==null || notebookId.trim().isEmpty()) {
		   throw new NotebookNotFoundException("笔记本ID空");
		}
		int n = notebookDao.countNotebookById(notebookId);
		if (n != 1) {
		   throw new NotebookNotFoundException("该笔记本不存在");
		}
		if (title==null || title.trim().isEmpty()) {
		   title = "新建笔记";
		}

		String id = UUID.randomUUID().toString();
		String statusId = "1";
    	String typeId = "1";
    	String body = "";
		Long time = System.currentTimeMillis();
		Note note = new Note(id, notebookId, userId, statusId, typeId, title, body, time, time);
		n = noteDao.addNote(note);
		if (n != 1) {
			throw new NoteNotFoundException("保存失败");
		}
		
		addStars(userId, 5);
		
		return note;
	}
	
	@Transactional
	public boolean updateNote(String noteId, String title, String body)
			throws NoteNotFoundException {
		if (noteId==null || noteId.trim().isEmpty()) {
			throw new NoteNotFoundException("笔记ID空");
		}
		Note note = noteDao.findByNoteId(noteId);
		if (note == null) {
			throw new NoteNotFoundException("该笔记不存在");
		}
		Note data = new Note();
		if (title!=null && !(title.equals(note.getTitle()))) {
			data.setTitle(title);
		}
		if (body!=null && !(body.equals(note.getBody()))) {
			data.setBody(body);
		}
		data.setId(noteId);
		data.setLastModifyTime(System.currentTimeMillis());
		int n = noteDao.updateNote(data);
		return n==1;
	}
	
	@Transactional
	public boolean moveNote(String noteId, String notebookId)
			throws NoteNotFoundException, NotebookNoteFoundException {
		if (noteId==null || noteId.trim().isEmpty()) {
			throw new NoteNotFoundException("笔记ID空");
		}
		Note note = noteDao.findByNoteId(noteId);
		if (note == null) {
			throw new NoteNotFoundException("该笔记不存在");
		}
		if (notebookId==null || notebookId.trim().isEmpty()) {
			throw new NotebookNotFoundException("笔记本ID空");
		}
		int  n = notebookDao.countNotebookById(notebookId);
		if (n != 1) {
			throw new NotebookNoteFoundException("笔记本不存在");
		}

		Note data = new Note();
		data.setId(noteId);
		data.setNotebookId(notebookId);
		data.setLastModifyTime(System.currentTimeMillis());
		n = noteDao.updateNote(data);
		return n==1;
	}
	
	@Transactional
	public boolean deleteNote(String noteId)
			throws NoteNotFoundException {
		if (noteId==null || noteId.trim().isEmpty()) {
			throw new NoteNotFoundException("笔记ID空");
		}
		Note note = noteDao.findByNoteId(noteId);
		if (note == null) {
			throw new NoteNotFoundException("该笔记不存在");
		}

		Note data = new Note();
		data.setId(noteId);
		data.setStatusId("0");
		data.setLastModifyTime(System.currentTimeMillis());
		int n = noteDao.updateNote(data);

		return n==1;
	}
	
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listDeleteNotes(String userId)
			throws UserNotFoundException {
		if (userId==null || userId.trim().isEmpty()) {
		   throw new UserNotFoundException("用户ID空"); 
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
		   throw new UserNotFoundException("该用户不存在");
		}
//		return noteDao.findDeleteNotesByUserId(userId);
		return noteDao.findNotes(userId, null, "0");
	}
	
	@Transactional
	public boolean replayNote(String noteId, String notebookId)
			throws NoteNotFoundException, NotebookNoteFoundException {
		if (noteId==null || noteId.trim().isEmpty()) {
			throw new NoteNotFoundException("笔记ID空");
		}
		Note note = noteDao.findByNoteId(noteId);
		if (note == null) {
			throw new NoteNotFoundException("该笔记不存在");
		}
		if (notebookId==null || notebookId.trim().isEmpty()) {
			throw new NotebookNotFoundException("笔记本ID空");
		}
		int  n = notebookDao.countNotebookById(notebookId);
		if (n != 1) {
			throw new NotebookNoteFoundException("笔记本不存在");
		}

		Note data = new Note();
		data.setId(noteId);
		data.setNotebookId(notebookId);
		data.setStatusId("1");
		data.setLastModifyTime(System.currentTimeMillis());
		n = noteDao.updateNote(data);

		return n==1;
	}
	
	@Transactional
	public int deleteNoteById(String... noteIds)
			throws NoteNotFoundException {
		for (String id : noteIds) {
			int n = noteDao.deleteNoteById(id);
			if (n != 1) {
				throw new NoteNotFoundException("ID错误");
			}
		}
		return noteIds.length;
	}
	
	/**
	 * 与方法{@link #deleteNoteById}实现的功能一样，只是底层dao的实现不一样
	 * @param ids
	 * @return
	 * @throws NoteNotFoundException
	 */
	@Transactional
	public int deleteNotes(String... ids)
			throws NoteNotFoundException {
		int n = noteDao.deleteNotes(ids);
		if (n != ids.length) {
			throw new NoteNotFoundException("笔记ID出现错误");
		}
		return n;
	} 

	@Transactional
	public boolean addStars(String userId, int stars)
			throws UserNotFoundException {
		if (userId==null || userId.trim().isEmpty()) {
		   throw new UserNotFoundException("用户ID空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
		   throw new UserNotFoundException("该用户不存在");
		}

		Stars st = starsDao.findStarsByUserId(userId);
		if (st == null) {
			String id = UUID.randomUUID().toString();
			st = new Stars(id, userId, stars);
			int n = starsDao.insertStars(st);
			if (n != 1) {
				throw new RuntimeException("失败");
			}
		} else {
			int n = st.getStars() + stars;
			st.setStars(n);
			n = starsDao.updateStars(st);
			if (n != 1) {
				throw new RuntimeException("失败");
			}
		}
		
		return true;
	}
}
