package com.howbuy.uaa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.persistence.User;

public class MainController extends MultiActionController {

	private String index;
	private String home;
	
	public ModelAndView main(HttpServletRequest request,HttpServletResponse response,User user)
	{
		User u = (User)request.getSession().getAttribute("USERSESSION");
		
		if(u == null)
			return new ModelAndView(index);
		else
			return new ModelAndView(home);
		
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
	
 
}
