package com.carl.class009;

public class SingleTon {

    private SingleTon() {
    }

    ;//私有
    private String someting;//设置内容

    public String getSometing() {
        return someting;
    }

    public void setSometing(String someting) {
        this.someting = someting;
    }

    //静态内部类,
    private static class SingleTonInner {
        private static SingleTon instance = new SingleTon();
    }

    // 暴露方法
    public static SingleTon getInstance() {
        return SingleTonInner.instance;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + SingleTon.getInstance().hashCode());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + SingleTon.getInstance().hashCode());
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + SingleTon.getInstance().hashCode());
            }
        }, "t3");

        t1.start();t2.start();t3.start();
    }
}
