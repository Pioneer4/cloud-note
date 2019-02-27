package name.electricalqzhang.cloud.note.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import name.electricalqzhang.cloud.note.entity.Note;

public interface NoteDao {
	List<Map<String, Object>> findNotesByNotebookId(String notebookId);
	Note findByNoteId(String noteId);
	int addNote(Note note); 
	int updateNote(Note note);
	List<Map<String, Object>> findDeleteNotesByUserId(String userId);
	int deleteNoteById(String noteId);
	int deleteNotes(@Param("ids") String... ids);
	List<Map<String, Object>> findNotes(
			@Param("userId") String userId,
			@Param("notebookId") String notebookId,
			@Param("statusId") String statusId);
}
