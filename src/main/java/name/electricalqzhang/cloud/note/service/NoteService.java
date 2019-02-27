package name.electricalqzhang.cloud.note.service;
import name.electricalqzhang.cloud.note.entity.Note;

import java.util.List;
import java.util.Map;

public interface NoteService {
	List<Map<String, Object>> listNotes(String notebookId)
			throws NotebookNoteFoundException;

	Note getNote(String noteId)
			throws NoteNotFoundException;

	Note addNote(String userId, String notebookId, String title)
			throws UserNotFoundException, NotebookNotFoundException;

	boolean updateNote(String noteId, String title, String body)
			throws NoteNotFoundException;

	boolean moveNote(String noteId, String notebookId)
			throws NoteNotFoundException, NotebookNoteFoundException;

	boolean deleteNote(String noteId)
			throws NoteNotFoundException;

	List<Map<String, Object>> listDeleteNotes(String userId)
			throws UserNotFoundException;

	boolean replayNote(String noteId, String notebookId)
			throws NoteNotFoundException, NotebookNoteFoundException;

	int deleteNoteById(String... noteIds)
			throws NoteNotFoundException;

	boolean addStars(String userId, int stars)
			throws UserNotFoundException;
}
