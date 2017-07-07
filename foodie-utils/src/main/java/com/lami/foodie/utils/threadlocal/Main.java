package com.lami.foodie.utils.threadlocal;

/**
 * Created by xujiankang on 2017/7/7.
 */
import java.net.*;

public class Main {

    public static void main(String...args) throws Exception {
        loadFoo();
        while (true) {
            System.gc();
            Thread.sleep(1000);
        }
    }

    private static void loadFoo() throws Exception {
        CustomClassLoader cl = new CustomClassLoader(null);
        Class<?> clazz = cl.loadClass("com.lami.foodie.utils.threadlocal.Main$Foo");
        clazz.newInstance();
        cl = null;
    }


    public static class Foo {
        private static final ThreadLocal<Foo> tl = new ThreadLocal<Foo>();

        public Foo() {
            tl.set(this);
            System.out.println("ClassLoader: " + this.getClass().getClassLoader());
        }
    }
}