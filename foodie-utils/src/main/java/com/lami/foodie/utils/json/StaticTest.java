package com.lami.foodie.utils.json;

import org.apache.log4j.Logger;

/**
 * Created by xujiankang on 2017/5/23.
 */
public class StaticTest {

    private static final Logger logger = Logger.getLogger(StaticTest.class);

    public static void main(String[] args) throws Exception{
        logger.info("Class.forName XiaoMin init");
        Class.forName(XiaoMin.class.getName());
        logger.info("Class.forName XiaoMin over");
        logger.info("");
        logger.info("Class.forName XiaoMin init");
        Class.forName(Person.class.getName());
        logger.info("Class.forName XiaoMin over");
    }

}
