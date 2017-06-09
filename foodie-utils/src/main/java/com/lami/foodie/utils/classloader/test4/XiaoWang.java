package com.lami.foodie.utils.classloader.test4;

/**
 * Created by xjk on 6/8/17.
 */
public class XiaoWang extends Person {

    static {

        System.out.println("****** init Class XiaoWang **********");
    }

    public int foo(){ return 1; }

    public Dog bar() { return new Dog(); }

}
