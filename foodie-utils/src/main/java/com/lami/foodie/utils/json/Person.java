package com.lami.foodie.utils.json;

import org.apache.log4j.Logger;

import java.util.Locale;

/**
 * Created by xujiankang on 2017/5/18.
 */
public class Person {

    private static final Logger logger = Logger.getLogger(Person.class);

    private String name;
    private String age;

    static {
        System.out.println("Person init static block");
    }


    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public static void main(String[] args) throws Exception{
        String PHONE = "^1(3|4|5|7|8)[0-9]\\d{8}$";
        System.out.println("17721170911".matches(PHONE));

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                logger.info("Thread 1 sleep begin");
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("Thread 1 sleep over");
            }
        });

        thread1.start();
        logger.info("thread1.join begin");
        thread1.join();
        logger.info("thread1.join end");

        logger.info("main thread execute");


        logger.info(String.format(Locale.ENGLISH, "%010d", 99));
        logger.info(String.format(Locale.ENGLISH, "%010d", 999));
        logger.info(String.format(Locale.ENGLISH, "%010d", 9999));
    }
}
