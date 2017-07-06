package com.lami.foodie.utils.concurrent;

/**
 * Created by xujiankang on 2017/7/6.
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable {

    Integer a = null;
    public void run() {
        a.intValue();
    }

    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
            System.out.println("该干嘛干嘛去");
        } catch (Exception e) {
            System.out.println("能不能捕获到异常？");
        }

    }

}
