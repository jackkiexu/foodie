package com.lami.foodie.utils.classloader.test3;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by xjk on 5/2/17.
 */
public class SimpleURLClassLoader extends URLClassLoader {

    public SimpleURLClassLoader(URL[] urls) {
        super(urls);
    }
}
