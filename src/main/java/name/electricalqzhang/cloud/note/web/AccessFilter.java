package name.electricalqzhang.cloud.note.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.electricalqzhang.cloud.note.entity.User;

/**
 * Servlet Filter implementation class AccessFilter
 */
public class AccessFilter implements Filter {

	private String login = "/log_in.html";

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		String path = req.getRequestURI();
		if (path.endsWith(login)) {
			chain.doFilter(request, response);
			return;
		}
		if (path.endsWith("alert_error.html")) {
			chain.doFilter(request, response);
			return;
		}
		User user = (User)session.getAttribute("loginUser");
		if (user == null) {
			res.sendRedirect(req.getContextPath() + login);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
