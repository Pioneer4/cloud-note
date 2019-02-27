package name.electricalqzhang.cloud.note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import name.electricalqzhang.cloud.note.entity.Note;
import name.electricalqzhang.cloud.note.service.NoteNotFoundException;
import name.electricalqzhang.cloud.note.service.NoteService;
import name.electricalqzhang.cloud.note.util.JsonResult;

@RequestMapping("/note")
@Controller("noteController")
public class NoteController  extends AbstractController {

	@Resource
	private NoteService noteService;

	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult list(String notebookId) {
		List<Map<String, Object>> list = noteService.listNotes(notebookId);
		return new JsonResult(list);
	}

	@RequestMapping("/load.do")
	@ResponseBody
	public JsonResult load(String noteId) {
		Note note = noteService.getNote(noteId);
		return new JsonResult(note);
	}

	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult add(String userId, String notebookId, String title) {
		Note note = noteService.addNote(userId, notebookId, title);
		return new JsonResult(note);
	}

	@RequestMapping("/update.do")
	@ResponseBody
	public JsonResult update(String noteId, String title, String body) {
		boolean success = noteService.updateNote(noteId, title, body);
		return new JsonResult(success);
	}

	@RequestMapping("/move.do")
	@ResponseBody
	public JsonResult moveNote(String noteId, String notebookId) {
		boolean success = noteService.moveNote(noteId, notebookId);
		return new JsonResult(success);
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult deleteNote(String noteId) {
		boolean success = noteService.deleteNote(noteId);
		return new JsonResult(success);
	}

	@RequestMapping("/trash.do")
	@ResponseBody
	public JsonResult listDeleteNotes(String userId) {
		List<Map<String, Object>> list = noteService.listDeleteNotes(userId);
		return new JsonResult(list);
	}

	@RequestMapping("/replay.do")
	@ResponseBody
	public JsonResult replayNote(String noteId, String notebookId) {
		boolean success = noteService.replayNote(noteId, notebookId);
		return new JsonResult(success);
	}

	@RequestMapping("/remove.do")
	@ResponseBody
	public JsonResult removeNote(String... noteIds) {
		int n = noteService.deleteNoteById(noteIds);
		return new JsonResult(n);
	}
}
