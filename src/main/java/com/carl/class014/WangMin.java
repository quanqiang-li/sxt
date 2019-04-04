package com.carl.class014;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class WangMin implements Delayed {
	// 下机时间,时间点概念,不是时间段,例如2019-04-04 13:00,不是13个小时
	private long endtime;
	// 姓名
	private String name;
	// 身份证
	private String idno;
	// 定义时间工具类,单位毫秒
	private TimeUnit tu = TimeUnit.MILLISECONDS;

	public long getEndtime() {
		return endtime;
	}

	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	// 相互比较排序
	@Override
	public int compareTo(Delayed o) {
		if (this.getDelay(tu) - o.getDelay(tu) > 0) {
			return 1;
		} else if (this.getDelay(tu) - o.getDelay(tu) == 0) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// 这里的TimeUnit并没有使用,但实现需要,表示还有多长毫秒结束
		long l = endtime - System.currentTimeMillis();
		//System.out.println("当前网民"+this.getName()+"还有时间:"+l);
		return endtime - System.currentTimeMillis();
	}

	public WangMin(long endtime, String name, String idno) {
		super();
		this.endtime = endtime;
		this.name = name;
		this.idno = idno;
	}

}
