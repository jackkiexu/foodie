package com.lami.foodie.utils.tomcat.collection;

import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by xujiankang on 2017/6/26.
 */
public class ConcurrentLinkedQueueTest {

    private static final Logger logger = Logger.getLogger(ConcurrentLinkedQueueTest.class);

    public static void main(String[] args) {
        // 利用 ConcurrentLinkedQueue 能保证存放元素是按照插入顺序来
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();

        concurrentLinkedQueue.add("A");
        concurrentLinkedQueue.add("B");
        concurrentLinkedQueue.add("C");
        concurrentLinkedQueue.add("D");
        concurrentLinkedQueue.add("E");
        concurrentLinkedQueue.add("F");

        Iterator<String> iterator = concurrentLinkedQueue.iterator();
        // 在进行 迭代时, 其实调用的是 ConcurrentLinkedQueue 的内部方法, 有能会丢掉正在插入的数据
        // 如想不会出现 上面出现的输出数据少的情况, 可以加大锁的粒度, 使用synchronized(Object) + 数组结合来完成上面的要求
        for(String first = iterator.next() ;iterator.hasNext();first = iterator.next()){
            logger.info("element:" + first);
        }
    }

}
