package com.lami.foodie.utils.privileged;

import org.apache.log4j.Logger;

/**
 * Created by xujiankang on 2017/6/7.
 */
public class Click2 {

    private static final Logger logger = Logger.getLogger(Click2.class);

    static {
        logger.info("Click2 init");
    }

    private static String name;

    public void say(String something){
        logger.info("say " + something);
    }

}
