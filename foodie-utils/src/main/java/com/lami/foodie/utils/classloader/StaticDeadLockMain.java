package com.lami.foodie.utils.classloader;

import org.apache.log4j.Logger;

/**
 * Created by xjk on 5/1/17.
 */
public class StaticDeadLockMain extends Thread {

    public static final Logger logger = Logger.getLogger(StaticDeadLockMain.class);

    public char flag;

    public StaticDeadLockMain(char flag) {
        this.flag = flag;
        this.setName("Thread " + flag);
    }

    @Override
    public void run() {
        try {
            Class.forName("com.lami.foodie.utils.classloader.Static"+flag);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.info(getName() + " over");
    }

    public static void main(String[] args) {
        StaticDeadLockMain staticDeadLockMainA = new StaticDeadLockMain('A');
        staticDeadLockMainA.start();
        StaticDeadLockMain staticDeadLockMainB = new StaticDeadLockMain('B');
        staticDeadLockMainB.start();
    }
}
