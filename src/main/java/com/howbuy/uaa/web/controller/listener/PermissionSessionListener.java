package com.howbuy.uaa.web.controller.listener;


import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.persistence.User;



public class PermissionSessionListener implements HttpSessionBindingListener {
	
	
	private long userid;
	
	public PermissionSessionListener(long userid){
		this.userid = userid;
	}
	
	public PermissionSessionListener(){}

	@SuppressWarnings("unchecked")
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		
		HttpSession session = event.getSession();
		String key = UaaContants.KEY_USERID;
		Set<User> userset= (Set<User>)session.getServletContext().getAttribute(key);			
		if(userset!=null && userset.size()>0){
			for(Iterator<User> it=userset.iterator();it.hasNext();){
				User u = (User)it.next();
				if(u.getId() == userid){
					it.remove();
				}
			}

		}	
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
