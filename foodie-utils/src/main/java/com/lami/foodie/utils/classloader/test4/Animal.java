package com.lami.foodie.utils.classloader.test4;

/**
 * Created by xjk on 6/8/17.
 */
public abstract class Animal {

    public String name;
    public abstract void doSomething();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
