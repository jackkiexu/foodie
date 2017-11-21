package com.lami.foodie.utils.threadlocal;

/**
 * Created by xjk on 7/7/17.
 */
public class Foo {

    private String name = "xjk";
    private static final ThreadLocal<Foo> tl = new ThreadLocal<Foo>();

    public Foo() {
        tl.set(this);
        System.out.println("ClassLoader: " + this.getClass().getClassLoader());
    }
}
