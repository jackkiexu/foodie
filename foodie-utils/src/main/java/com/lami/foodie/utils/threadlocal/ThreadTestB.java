package com.lami.foodie.utils.threadlocal;

import org.apache.log4j.Logger;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by xujiankang on 2017/1/12.
 */
public class ThreadTestB {

    private static final Logger logger = Logger.getLogger(ThreadTestB.class);

    static class ThreadDemo extends Thread{
        @Override
        public void run() {
            logger.info("in : " + getName());
            LockSupport.park();
            if(Thread.interrupted()){
                logger.info(getName() + " 被中断了");
            }
            logger.info(getName() + " 执行结束");
        }
    }


    public static void main(String[] args) throws Exception{
        ThreadDemo t1 = new ThreadDemo();
        ThreadDemo t2 = new ThreadDemo();

        t1.start();

        t2.start();
        Thread.sleep(200);
        t1.interrupt();
        Thread.sleep(200);
        LockSupport.unpark(t2);
    }

}
