package com.lami.foodie.utils.future;

import org.apache.log4j.Logger;
import java.util.concurrent.*;

/**
 * Created by xjk on 12/17/16.
 */
public class FutureTaskTest {

    private static final Logger logger = Logger.getLogger(FutureTaskTest.class);

    static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

    public static void main(String[] args) {

        final FutureTask<?> futureTask = new FutureTask<Object>(new Callable<Object>() {
            public Object call() throws Exception {
                logger.info("睡觉开始");
                try {
                    Thread.sleep(3*1000); // 模拟计算(耗费3秒)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("睡觉结束");
                return "futureTask compute over";
            }
        });

        new Thread(){ // 启动futureTask 的任务
            @Override
            public void run() {
                futureTask.run();
            }
        }.start();

        new Thread(){
            @Override
            public void run() { // 获取 future 的执行结果, 最多等待2秒
                try {
                    logger.info("future.get() " + futureTask.get(2, TimeUnit.SECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

        new Thread(){
            @Override
            public void run() { // 获取 future 的执行结果, 最多等待4秒
                try {
                    logger.info("future.get()" + futureTask.get(4, TimeUnit.SECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
