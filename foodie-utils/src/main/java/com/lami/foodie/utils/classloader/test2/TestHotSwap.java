package com.lami.foodie.utils.classloader.test2;

/**
 * Created by xjk on 5/2/17.
 */
public class TestHotSwap {

    public static void main(String[] args) throws Exception {
        //开启线程，如果class文件有修改，就热替换
        Thread t = new Thread(new MonitorHotSwap());
        t.start();
    }

}
