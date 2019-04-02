package com.carl.class007;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Communication3 {
    private volatile List<String> list = new ArrayList<String>(10);

    public void add() {
        list.add("1qaz");
    }

    public int size() {
        return list.size();
    }


    public static void main(String[] args) {
        final Communication3 communication = new Communication3();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        //线程1负责添加数据
        final Thread thread1 = new Thread(new Runnable() {
            public void run() {
                    for (int i = 0; i < 10; i++) {
                        communication.add();
                        System.out.println("当前线程" + Thread.currentThread().getName() + "添加了一个元素");
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            if (communication.size() == 5) {
                                countDownLatch.countDown();//计数器减一
                                System.out.println("已经发出通知");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }, "t1");
        //线程2,判断volatile修饰的list大小为5则停止
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                    if (communication.size() != 5) {
                        try {
                            countDownLatch.await();//等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程" + Thread.currentThread().getName() + "接到通知,线程终止");
                    throw new RuntimeException();
            }
        }, "t2");
        thread2.start();//先启动t2,否则t1先运行,知道结束才轮到t2,那么t2判断长度等于5就不可能了
        thread1.start();
    }
}
