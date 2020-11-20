package com.java.xknowledge.se.thread.threadpool.executors;

class MyRunnable implements Runnable {
    private int i;
    private int simulateTime;

    public MyRunnable(int i) {
        this.i = i;
    }

    public MyRunnable(int i, int simulateTime) {
        this(i);
        this.simulateTime = simulateTime;
    }

    @Override
    public void run() {
        System.out.println("currentTime = " + System.currentTimeMillis() + "，第 " + this.i +
                " Runnable，使用线程" + Thread.currentThread().getName() + "开始执行....");
        try {
            Thread.sleep(simulateTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("currentTime = " + System.currentTimeMillis() + "，第 " + this.i +
                " Runnable，使用线程" + Thread.currentThread().getName() + "执行完毕----");
    }
}
