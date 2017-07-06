package com.lami.foodie.utils.concurrent;

import java.util.concurrent.*;

/**
 * Created by xujiankang on 2017/7/6.
 */
public class ThreadPoolExecutorTest {


    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        final Integer a = null;

        Callable callable = new Callable<Object>() {
            public Object call() throws Exception {
                a.intValue();
                return null;
            }};

        Future<Object> future = executorService.submit(callable);

        System.out.println(future.get(10, TimeUnit.SECONDS));
    }

}
