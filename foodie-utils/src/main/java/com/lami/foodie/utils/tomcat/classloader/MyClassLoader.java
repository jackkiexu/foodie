package com.lami.foodie.utils.tomcat.classloader;

import com.lami.foodie.utils.privileged.Client;
import org.apache.log4j.Logger;
import sun.reflect.Reflection;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by xujiankang on 2017/6/8.
 */
public class MyClassLoader extends URLClassLoader {

    private static final Logger logger = Logger.getLogger(MyClassLoader.class);

    // 工程class类所在的路径
    private static String classPath = MyClassLoader.class.getResource("/").getPath();

    public MyClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
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
        logger.info("*************************test MyClassLoader begin");
        String classPath = MyClassLoader.class.getResource("/").getPath();
        System.out.println("classPath: " + classPath);
        MyClassLoader myClassLoader = new MyClassLoader(getURLs(classPath));
        Class clazz = myClassLoader.loadClass("com.lami.foodie.utils.privileged.Client", true);
        Client client = (Client)clazz.newInstance();
        System.out.println(client.chick);
//        Class clazz2 = myClassLoader.loadClass("com.lami.foodie.utils.privileged.Client", true);
//        Client client = (Client)clazz.newInstance();
//        clazz.getFields();
        logger.info("****************************test MyClassLoader over");
        System.out.println(MyClassLoader.class.getResource(""));
        System.out.println(MyClassLoader.class.getResource("/"));
        InputStream url = MyClassLoader.class.getResourceAsStream("/com/lami/foodie/utils/tomcat/classloader/Param.class");
        InputStream url2 = MyClassLoader.class.getResourceAsStream("/com/lami/foodie/utils/tomcat/classloader/Param");

        Class<?> caller = Reflection.getCallerClass();
        Class.forName("com/lami/foodie/utils/tomcat/classloader/Param");
        System.out.println("caller:" + caller);
        System.out.println("url:" + url);
        System.out.println("url2:" + url2);

    }
}
