package com.lami.foodie.utils.classloader;

import org.apache.log4j.Logger;

/**
 * Created by xjk on 5/1/17.
 */
public class StaticA {

    public static final Logger logger = Logger.getLogger(StaticA.class);

    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("com.lami.foodie.utils.classloader.StaticB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        logger.info("StaticA init OK");
    }

}

