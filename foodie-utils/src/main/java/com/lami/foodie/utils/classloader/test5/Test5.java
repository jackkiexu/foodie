package com.lami.foodie.utils.classloader.test5;


import com.lami.foodie.utils.classloader.test4.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xujiankang on 2017/7/6.
 */
public class Test5 {

    public static Map pool = new HashMap();
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        for (int i=0;i<10000000;i++){
            test(args);
        }
    }

    public static void test(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader cl = Test5.class.getClassLoader();
        String className = "com.lami.foodie.utils.classloader.test4.Person";

        @SuppressWarnings("unchecked")
        Class<Person> clazz = (Class<Person>) cl.loadClass(className);
        Person p = clazz.newInstance();
        p.setName("qiang");
        pool.put(System.nanoTime(), p);
        cl = p.getClass().getClassLoader();
    }



}
