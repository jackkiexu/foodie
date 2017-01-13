package com.lami.foodie.utils.threadlocal;

import org.apache.log4j.Logger;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by xujiankang on 2017/1/12.
 */
public class ThreadTest {

    private static final Logger logger = Logger.getLogger(ThreadTest.class);

    public static void main(String[] args) throws Exception{

        Thread thread = new Thread(){
            @Override
            public void run() {
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        logger.info(" I am Interrupted");
                        break;
                    }

                    try {
                        Thread.sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
//                        logger.info(interrupted());
                        logger.info("Thread is sleeping");
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }

                logger.info(getName() + " 执行完成");
            }
        };

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

        thread.join();

        logger.info("*****************************************************************");

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        logger.info(" I am Interrupted");
                        break;
                    }

                    try {
                        logger.info(" LockSupport.park(this  开始阻塞");
                        LockSupport.park(this);
                        logger.info(interrupted());
                    }catch (Exception e){
                        e.printStackTrace();
                        logger.info("Thread is sleeping");
                        logger.info(Thread.currentThread().isInterrupted());
                        Thread.currentThread().interrupt();
                        logger.info(Thread.currentThread().isInterrupted());
                    }
                }
            }
        };

        thread2.start();
        Thread.sleep(1000);
        thread2.interrupt();


    }
}
