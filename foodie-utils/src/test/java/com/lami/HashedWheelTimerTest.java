package com.lami;

import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.TimerTask;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by xujiankang on 2017/11/22.
 */
public class HashedWheelTimerTest {

    @Test
    public void test(){
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("OK");
            }
        }, 100, TimeUnit.MILLISECONDS);

        hashedWheelTimer.start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
