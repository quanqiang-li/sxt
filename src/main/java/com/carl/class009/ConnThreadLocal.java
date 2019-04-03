package com.carl.class009;

public class ConnThreadLocal {
    private static ThreadLocal<String> tl = new ThreadLocal<String>();

    // threadlocal 放值,只能放一个值
    public void setValue(String value) {
        tl.set(value);
    }

    // 从ThreadLocal取值,直接get,取得就是当前线程放进去的值
    public String getValue() {
        System.out.println(Thread.currentThread().getName() + ":" + this.tl.get());
        return this.tl.get();
    }

    public static void main(String[] args) {
        final ConnThreadLocal connThreadLocal = new ConnThreadLocal();
        Thread t1  = new Thread(new Runnable() {
            public void run() {
                connThreadLocal.setValue("张三");//不改变全局的值
                connThreadLocal.getValue();
            }
        }, "t1");

        t1.start();

        Thread t2  = new Thread(new Runnable() {
            public void run() {
                connThreadLocal.getValue();//null,
            }
        }, "t2");

        t2.start();
    }
}
