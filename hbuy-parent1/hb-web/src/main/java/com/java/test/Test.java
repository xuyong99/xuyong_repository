package com.java.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Xyong
 * @since: 2019/10/15
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService p = Executors.newFixedThreadPool(5);
        MyTask myTask1 = new MyTask();
    }
}
