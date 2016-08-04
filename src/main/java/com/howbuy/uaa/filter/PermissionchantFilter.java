package com.howbuy.uaa.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.service.PermissionManagerService;

/**
 * 权限校验拦截器
 * @author yichao.song
 * 
 */
public class PermissionchantFilter implements Filter {
	
	final static Logger logger = LoggerFactory.getLogger(PermissionchantFilter.class);
	private PermissionManagerService permissionManagerService;
	public static List<Permission> allPermission = new ArrayList<Permission>();
	public static Map<String,Object> map = new HashMap<String,Object>();	
	

	public void setPermissionManagerService(
			PermissionManagerService permissionManagerService) {
		this.permissionManagerService = permissionManagerService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.debug("enter PermissionchantFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String contextPath = httpRequest.getContextPath();
		String url = httpRequest.getServletPath();
		
			
		@SuppressWarnings("unchecked")
		List<Permission> userPermission = (List<Permission>) httpRequest.getSession().getAttribute("ALL_PERMISSION");
		boolean flag = false;
		for (Permission permission : allPermission) {
			String perUrl = permission.getUrl();
			if (url.equals(perUrl)) {
				flag = true;
				break;
			}
		}
		Set<String> set = new HashSet<String>();
		if (userPermission != null && userPermission.size() > 0) {
			for (Permission per : userPermission) {
				String perUrl = per.getUrl();
				if (perUrl != null && !"".equals(perUrl)) {
					set.add(perUrl);
				}
			}
		}
		if (flag) {
			if (!set.contains(url)) {
				httpResponse.sendRedirect(contextPath + "/login/pererror.htm");
				return;
			}
			
		}

		if (!response.isCommitted()) {

			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		allPermission = permissionManagerService.queryPermission("", "0");
	}

	public static List<Permission> getAllPermission(){
		return allPermission;
	}
	public static void setAllPermission(List<Permission> allPermission){
		PermissionchantFilter.allPermission = allPermission;
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	


}
