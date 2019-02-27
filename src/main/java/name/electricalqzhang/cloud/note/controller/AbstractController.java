package name.electricalqzhang.cloud.note.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import name.electricalqzhang.cloud.note.util.JsonResult;

public abstract class AbstractController {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult handleException(Exception e) {
		 e.printStackTrace();
		 return new JsonResult(e);
	}

}