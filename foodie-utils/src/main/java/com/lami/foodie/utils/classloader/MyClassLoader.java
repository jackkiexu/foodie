package com.lami.foodie.utils.classloader;

import com.lami.foodie.utils.classloader.test2.HotSwapURLClassLoader;
import com.lami.foodie.utils.classloader.test4.XiaoWang;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by xjk on 6/8/17.
 */
public class MyClassLoader extends URLClassLoader {

    // 工程class类所在的路径
    private static String classPath = MyClassLoader.class.getResource("").getPath();

    public MyClassLoader(URL[] urls) {
        super(getURLs(classPath));
    }

    private static URL[] getURLs(String classPath) {
        try {
            URL url = new File(classPath).toURI().toURL();
            MyClassLoader.classPath = classPath;
            return new URL[] { url };
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader(null);
        Class clazz = myClassLoader.loadClass("com.lami.foodie.utils.classloader.test4.XiaoWang", true);
//        ((XiaoWang)clazz.newInstance()).bar();
        System.out.println("*************************** over1");
        Class.forName("com.lami.foodie.utils.classloader.test4.XiaoWang");
        System.out.println("*************************** over2");
        URL springURL = myClassLoader.getResource("spring.properties");
        System.out.println("springURL:" + springURL);
    }


}
