package com.carl.class017;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolDemo {

	public static void main(String[] args) {
		int corePoolSize = 5;
		int maximumPoolSize = 10;
		long keepAliveTime = 60;
		TimeUnit unit = TimeUnit.SECONDS;
		//有界队列,新任务提交,当前线程数(t),若 t<corePoolSize,则直接创建线程,
		//若t>corePoolSize时,则加入队列,队列满了,则创建线程,若t>maximumPoolSize则执行拒绝策略
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
		//无界队列,新任务提交,当前线程数(t),若 t<corePoolSize,则直接创建线程,
		//若t>corePoolSize时,则加入队列直到资源耗尽,不存在入队失败的情况,
		//BlockingQueue<Runnable> workQueue2 = new LinkedBlockingQueue<>();
		
		ThreadFactory threadFactory = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				//自定义线程分组名称
				Thread thread = new Thread(new ThreadGroup("myThreadGroup"),r);
				System.out.println("生成线程 "+thread);
				return thread;
			}
		};
		//丢弃队列里最早的任务,新任务加入队列的,ThreadPoolExecutor.poll();ThreadPoolExecutor.execute(runnable)
		//RejectedExecutionHandler handler1 = new ThreadPoolExecutor.DiscardOldestPolicy();
		RejectedExecutionHandler handler = new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println("自定义的拒绝策略,可以记录任务到队列或数据库,后续再处理");
			}
		};
		new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}
}
