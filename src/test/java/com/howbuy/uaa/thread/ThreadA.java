/**
 * 
 */
package com.howbuy.uaa.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiankun.li
 *
 */
public class ThreadA {

	
	private List<String> list = new ArrayList<String>(0);
	public void addA (String s){
		synchronized (list) {
			list.add(s);
			System.out.println(Thread.currentThread().getName()+"-add-"+s);
		}
	}
	
	public void get(){
		synchronized (list) {
			for (int i = 0; i < list.size(); i++) {
				String s = list.get(i);
				System.out.println(Thread.currentThread().getName()+"-get-"+s);
			}
			list = new ArrayList<String>(0);
		}
		
	}
	
	
}
