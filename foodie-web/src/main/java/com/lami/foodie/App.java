package com.lami.foodie;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xjk on 2016/10/11.
 */
public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    public static String say(String name){
        return "00";
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/springContent.xml");
        Object object = applicationContext.getBean("testAOP");

        logger.info("object:" + object);
    }

}
