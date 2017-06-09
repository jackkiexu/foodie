package com.lami.foodie.utils.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by xujiankang on 2017/5/3.
 */
public class Student {

    private static final Logger logger = LoggerFactory.getLogger(Student.class);

    public void sayHello(){
        logger.info("sayHello");
    }

    public static void main(String[] args) throws Exception{
        logger.info(Charset.availableCharsets().toString());
        logger.info(Charset.isSupported("GBK") + "");
        logger.info(Charset.defaultCharset() + "");
        System.out.println("aaa".getBytes("gbk"));
        logger.info("----------------------------------------------");

        Environment.logEnv("Student - ", logger);
    }

}
