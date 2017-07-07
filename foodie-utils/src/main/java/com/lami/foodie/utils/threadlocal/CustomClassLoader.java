package com.lami.foodie.utils.threadlocal;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by xujiankang on 2017/7/7.
 */
public class CustomClassLoader extends URLClassLoader {

    // 工程class类所在的路径
    private static String classPath = CustomClassLoader.class.getResource("").getPath();

    public CustomClassLoader(URL[] urls) {
        super(getURLs(classPath));
    }

    private static URL[] getURLs(String classPath) {
        try {
            URL url = new File(classPath).toURI().toURL();
            CustomClassLoader.classPath = classPath;
            return new URL[] { url };
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void finalize() {
        System.out.println("*** CustomClassLoader finalized!");
    }

    @Override
    public String toString() {
        return "CustomClassLoader{}";
    }
}