package com.lami.foodie.utils.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xujiankang on 2017/7/7.
 */
public class FinalTest {


    private static final int HASH_INCREMENT = 0x61c88647;
    private static AtomicInteger nextHashCode = new AtomicInteger();
    private static final int threadLocalHashCode = nextHashCode();

    /**
     * Returns the next hash code.
     */
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest();
        for(int i = 0; i < 10; i++){
            System.out.println(finalTest.threadLocalHashCode);
        }
    }

}
