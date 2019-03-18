package com.xyz.MySystem.util.threadPool;

import java.util.concurrent.ThreadFactory;

public class CommonThreadFactory implements ThreadFactory{

	private String threadName;
	
	public CommonThreadFactory(String threadName) {
		this.threadName = threadName;
	}
	
	@Override
	public Thread newThread(Runnable r) {
		
		Thread thread = new Thread(threadName);
		return thread;
	}

}
