package com.howbuy.uaa.core;


public class QuartzTest {
	
	private int count = 0;
	public void run(){
		count++;
		System.out.println("----------count:" + count);
	}
}
