package com.lami.foodie.utils;

import org.apache.log4j.Logger;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by xjk on 12/16/16.
 */
public class LockSupportTest {

    private static final Logger logger = Logger.getLogger(LockSupportTest.class);

    static class ATask implements Runnable{

        public void run() {
            for(int i = 0; i < 9999999; i++){
                LockSupport.park();
                System.out.println("thread " + Thread.currentThread().getId() + " awake!");
            }
        }
    }

    public static void main(String[] args)throws Exception {

        Thread thread = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 9999999; i++){
                    LockSupport.park();
                    System.out.println("thread " + Thread.currentThread().getId() + " awake!");
                    try {
                        logger.info(Thread.currentThread().interrupted());
                        Thread.sleep(10*1000);
                    } catch (Exception e) {
                        logger.info("Exception**************");
                        e.printStackTrace();
                    }

                    logger.info(Thread.currentThread().interrupted());
                    try {
                        logger.info("gonging slee");
                        Thread.sleep(1*1000);
                        logger.info("gonging slee over");
                    } catch (Exception e) {
                        logger.info("Exception**************");
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
//        LockSupport.park(thread);

        Thread.sleep(3 * 1000);
        logger.info("thread.interrupt begin");
            thread.interrupt();
        logger.info("thread.interrupt end");
        Thread.sleep(3 * 1000);


    }

    public static void main2(String[] args) throws InterruptedException {
        final Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("init");
                LockSupport.park();
                System.out.println("thread " + Thread.currentThread().getId() + " awake!");
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().interrupted());
                System.out.println(Thread.currentThread().interrupted());
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });

        t.start();
        Thread.sleep(3000);

        // 2. 中断
        t.interrupt();
    }

}
