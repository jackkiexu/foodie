package com.lami.foodie.utils.concurrent;

import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by xjk on 12/19/16.
 */
public class ConcurrentLinkedListTest {

    private static final Logger logger = Logger.getLogger(ConcurrentLinkedListTest.class);

    public static void main(String[] args) {

        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

        queue.offer("a");
        queue.offer("b");
        queue.offer("c");

        logger.info(queue.poll());
        logger.info(queue.remove("b"));
    }

}
