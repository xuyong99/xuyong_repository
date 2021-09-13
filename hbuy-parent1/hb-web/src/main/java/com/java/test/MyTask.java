package com.java.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Xyong
 * @since: 2019/10/15
 */
public class MyTask implements Runnable {
    public MyTask() {
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        MyTask myTask1 = new MyTask();
        MyTask myTask2 = new MyTask();
        MyTask myTask3 = new MyTask();
        MyTask myTask4 = new MyTask();
        MyTask myTask5 = new MyTask();


        pool.execute(myTask1);
        pool.execute(myTask2);
        pool.execute(myTask3);
        pool.execute(myTask4);
        pool.execute(myTask5);

    }
}
