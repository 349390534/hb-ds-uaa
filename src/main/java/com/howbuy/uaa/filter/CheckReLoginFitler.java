/**
 * 
 */
package com.howbuy.uaa.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.persistence.User;

/**
 * 会话登录拦截器
 * 
 * @author qiankun.li
 * 
 */
public class CheckReLoginFitler implements Filter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CheckReLoginFitler.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LOGGER.debug("enter CheckReLoginFitler");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		if(uri.indexOf("/login")>-1){
			chain.doFilter(request, response);
			return;
		}
		String contextPath = httpRequest.getContextPath();
		User user = (User) httpRequest.getSession().getAttribute("USERSESSION");
		if (user == null) {
			LOGGER.info("CheckReLoginFitler:{}", "请求过期");
			String ajax = request.getParameter("ajax");
			if ("true".equalsIgnoreCase(ajax)) {
				httpResponse.sendRedirect(contextPath + "/login/isSessionOut.htm?out=1");
			} else {
				httpResponse.sendRedirect(contextPath + "/login/index.htm");
			}
			return;
		}
		String key = UaaContants.KEY_USERID;
		@SuppressWarnings("unchecked")
		Set<User> userset = (Set<User>) httpRequest.getSession().getServletContext().getAttribute(key);
		if (userset != null && userset.size() > 0) {
			for (Iterator<User> it = userset.iterator(); it.hasNext();) {
				User u = (User) it.next();
				if (u.getId() == user.getId()) {
					it.remove();
					httpRequest.getSession().invalidate();
					httpResponse.sendRedirect(contextPath + "/login/index.htm");
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
