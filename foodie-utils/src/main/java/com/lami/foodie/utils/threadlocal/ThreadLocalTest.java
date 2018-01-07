package com.lami.foodie.utils.threadlocal;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 参考资料
 * https://github.com/pzxwhc/MineKnowContainer/issues/20
 * Created by xujiankang on 2017/7/18.
 */
public class ThreadLocalTest {

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        threadLocal.set(new Integer(199));

        Thread thread = new MyThread();
        thread.start();
        System.out.println("main = " + threadLocal.get());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread = " + threadLocal.get());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
