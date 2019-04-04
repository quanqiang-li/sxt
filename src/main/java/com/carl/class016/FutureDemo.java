package com.carl.class016;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureDemo {

	public static void main(String[] args) throws Exception {
		// 线程池
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		// 任务1
		Callable<String> task1 = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "task1 耗时2s";
			}
		};
		// 任务2
		Callable<String> task2 = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(3000);
				return "task2 耗时3s";
			}
		};
		long start = System.currentTimeMillis();
		// 可以单个提交任务
		// Future<String> submit = executorService.submit(task1);
		// 下面是批量提交任务
		List<Callable<String>> taskList = Arrays.asList(task1, task2);
		List<Future<String>> futures = executorService.invokeAll(taskList);
		for (Future<String> future : futures) {
			System.out.println(future.get(5, TimeUnit.SECONDS));// get会阻塞,直到所有任务执行完毕,这里给定了超时时间
		}
		long end = System.currentTimeMillis();
		System.out.println("总耗时:" + (end - start) / 1000 + "秒");
		// 不再接收新任务,已提交任务执行完毕后关闭线程池,生产可不关闭
		executorService.shutdown();
	}
}
