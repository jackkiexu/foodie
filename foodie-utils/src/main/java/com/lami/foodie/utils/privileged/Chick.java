package com.lami.foodie.utils.privileged;

import org.apache.log4j.Logger;

/**
 * Created by xujiankang on 2017/6/7.
 */
public class Chick {

    static {
        System.out.println("Click init");
    }

    private static String name;

    public void say(String something){
        System.out.println("say " + something);
    }
}
