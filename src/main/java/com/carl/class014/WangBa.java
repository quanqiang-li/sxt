package com.carl.class014;

import java.util.concurrent.DelayQueue;
/**
 * 延迟队列,还是要执行queue.take
 * @author aisino
 *
 */
public class WangBa implements Runnable{

	private DelayQueue<WangMin> queue = new DelayQueue<>();
	private boolean yingye= true;
	
	public void shangji(int money,String name,String idno){
		//1块钱上机1秒 ^O^ 
		WangMin wangMin = new WangMin(money*1000+System.currentTimeMillis(), name, idno);
		System.out.println("网民:"+ name+"身份证:"+idno+"上机,交钱:" + money);
		queue.add(wangMin);
	}
	
	public void xiaji(WangMin wangMin){
		System.out.println("网民:"+ wangMin.getName()+"身份证:"+wangMin.getIdno()+"时间到,下机");
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000*6);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(yingye){
			try {
				WangMin take = queue.take();	
				xiaji(take);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		System.out.println("网吧营业开始...");
		WangBa wangBa = new WangBa();
		//另开线程,监控下机
		Thread t1 = new Thread(wangBa, "t1");
		t1.start();
		//主线程,上机
		wangBa.shangji(1, "1", "123");
		wangBa.shangji(10, "10", "456");
		wangBa.shangji(5, "5", "789");
	}
}
