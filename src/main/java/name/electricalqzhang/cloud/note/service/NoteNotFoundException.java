package name.electricalqzhang.cloud.note.service;

public class NoteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoteNotFoundException() {
	}

	public NoteNotFoundException(String arg0) {
		super(arg0);
	}

	public NoteNotFoundException(Throwable arg0) {
		super(arg0);
	}

	public NoteNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NoteNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
