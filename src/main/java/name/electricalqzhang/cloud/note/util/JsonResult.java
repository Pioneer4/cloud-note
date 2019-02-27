package name.electricalqzhang.cloud.note.util;

import java.io.Serializable;

import name.electricalqzhang.cloud.note.service.PasswordException;
import name.electricalqzhang.cloud.note.service.UserNameException;
import name.electricalqzhang.cloud.note.service.UserNotFoundException;

public class JsonResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	
	private int state;
	/* 错误信息 */
	private String message;
	/* 正确信息 */
	private Object data;
	
	public JsonResult() {
		
	}
	
	public JsonResult(String error) {
		state = ERROR;
		this.message = error;
	}
	
	public JsonResult(Object data) {
		state = SUCCESS;
		this.data = data;
	}
	
	public JsonResult(Throwable e) {
		state = ERROR;
		if (e instanceof UserNotFoundException) {
			state = 2;
		} else if (e instanceof PasswordException) {
			state = 3;
		} else if (e instanceof UserNameException) {
			state = 4;
		}
		message = e.getMessage();
	}
	

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
