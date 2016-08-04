package com.howbuy.uaa.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.common.util.StringUtil;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.User;
import com.howbuy.uaa.service.UserManagerService;
import com.howbuy.uaa.utils.JsonParse;

public class InterFaceCheckController extends MultiActionController {

	private static String STATUS = "status";

	private static String APP_KEY = "appkey";
	
	private static String USER_ID = "userid";

	private static String PERMISSION_ID = "pid";

	private UserManagerService userManagerService;

	private String appVal;

	public void setAppVal(String appVal) {
		this.appVal = appVal;
	}

	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}

	public void check(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Map retMap = new HashMap();

		User user = (User) request.getSession().getAttribute("USERSESSION");

		if (user != null) {// 登入状态

			boolean hasPerm = false;

			String pid = request.getParameter(PERMISSION_ID);

			if (StringUtil.isEmpty(pid)) {

				logger.warn("no pid");

				// 无效参数
				retMap.put(STATUS, "4");
			} else {

				List<Permission> userPers = (List<Permission>) request
						.getSession().getAttribute("ALL_PERMISSION");

				for (Permission p : userPers) {

					if (p.getPermissionId().equals(pid)) {

						hasPerm = true;

						break;
					}
				}

				if (hasPerm) {// 有权限
					
					User u = (User)request.getSession().getAttribute("USERSESSION");
					retMap.put(USER_ID, u.getUserName());
					retMap.put(STATUS, "3");
					retMap.put(APP_KEY, appVal);

				} else {// 无权限

					retMap.put(STATUS, "2");
				}
			}

		} else {// 未登陆

			retMap.put(STATUS, "1");

		}

		writeResp(request,response, retMap);

	}

	/*
	 * 返回客户端是否有会话，调用接口是否有权限
	 * 
	 * @return status 1:未登入 2：已登入没权限 3：已登入有权限
	 */
	private void writeResp(HttpServletRequest request,HttpServletResponse response, Map retMap)
			throws IOException {
		
		boolean jsonP = false;
		
		String cb = request.getParameter("callback");
		if (cb != null) {
		    jsonP = true;
		    response.setContentType("text/javascript");
		} else {
		    response.setContentType("application/x-json");
		}
		
		String ret = JsonParse.objToJsonStr(retMap);
		
		
		Writer out = response.getWriter();
		
		if (jsonP) {
		    out.write(cb + "(");
		}
		out.write(ret);
		
		if (jsonP) {
		    out.write(");");
		}

	}

}
