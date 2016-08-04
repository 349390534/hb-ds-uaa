package com.howbuy.uaa.core;


public abstract class QuartzTestDependency/* implements ApplicationContextAware*/{
	
//	private ApplicationContext context;
	
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext)
//			throws BeansException {
//		this.context=applicationContext;
//		
//	}

	protected abstract QuartzTest createQuartzTest();
	
//	public void run(){
//		QuartzTest test = (QuartzTest)context.getBean("QuartzTester");
//		System.out.println("---------create " + test);
//		test.run();
//	}
	public void run(){
		QuartzTest test = createQuartzTest();
		System.out.println("---------create " + test);
		test.run();
	}
}
