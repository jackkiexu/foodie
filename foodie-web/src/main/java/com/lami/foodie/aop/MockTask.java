package com.lami.foodie.aop;

/**
 * Created by xjk on 10/1/17.
 */
public class MockTask implements ITask {

    public void execute(Object object) {
        System.out.println("Object :" + object);
    }
}
