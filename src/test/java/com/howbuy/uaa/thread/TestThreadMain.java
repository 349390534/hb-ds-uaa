/**
 * 
 */
package com.howbuy.uaa.thread;

/**
 * @author qiankun.li
 *
 */
public class TestThreadMain {
	
	static ThreadA a = new ThreadA();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(){

			@Override
			public void run() {
				while(true){
					for(int i=0;i<5;i++){
						a.addA("I:"+i);
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();
		
		new Thread(){

			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					a.get();
				}
				
			}
			
			
		}.start();
		
		
		
	}

}
