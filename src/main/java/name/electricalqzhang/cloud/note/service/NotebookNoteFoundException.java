package name.electricalqzhang.cloud.note.service;

public class NotebookNoteFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotebookNoteFoundException() {
		super(); 
	}

	public NotebookNoteFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public NotebookNoteFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NotebookNoteFoundException(String arg0) {
		super(arg0);
	}

	public NotebookNoteFoundException(Throwable arg0) {
		super(arg0);
	}

}
