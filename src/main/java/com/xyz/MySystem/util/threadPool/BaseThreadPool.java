package com.xyz.MySystem.util.threadPool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;



/**
 * @author XuChongGuang
 *
 *	创建线程池的工具类
 *	1.除非特殊场景，线程池都不允许使用无界队列
 *	
 */
public class BaseThreadPool {
	/*
	 * 						  int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler
	 * */
	/** 保存线程池的map*/
	private final static Map<String, ThreadPoolExecutor> poolMap = new ConcurrentHashMap<>();

	private final static TimeUnit unit = TimeUnit.MINUTES ;
	
	private final static Logger logger = LoggerFactory.getLogger(BaseThreadPool.class);
	/**
	 * 	可以让开发人员定制threadpool
	 * */
	public static synchronized ThreadPoolExecutor getPool (String name , int corePoolSize , int maxSize) {
		if(poolMap.containsKey(name)) {
			return poolMap.get(name);
		}
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize , maxSize , 10 , unit , new ArrayBlockingQueue<Runnable>(10) ,new CommonThreadFactory("thread-dataSearch"), new ThreadPoolExecutor.AbortPolicy());
		poolMap.put(name, pool);
		return pool;
	}
	
	public static synchronized void addTask (String name , Runnable task) {
		if(poolMap.containsKey(name)) {
			poolMap.get(name).execute(task);
		}else {
			getPool(name , 5 , 10).execute(task);
		}
	}

	public static Map<String, ThreadPoolExecutor> getPoolmap() {
		return poolMap;
	}
	
	
}
