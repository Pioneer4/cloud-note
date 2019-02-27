	package name.electricalqzhang.cloud.note.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import name.electricalqzhang.cloud.note.entity.User;
import name.electricalqzhang.cloud.note.util.JsonResult;

@Component
public class AccessInterceptor implements HandlerInterceptor {


	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			String uri = request.getRequestURI();
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("loginUser");
			if (user == null) {
				JsonResult result = new JsonResult("需要重新登录");
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(result);
				response.getWriter().println(json);
				response.flushBuffer();
				return false;
			}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
